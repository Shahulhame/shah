package com.firstdata.recon.item;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.firstdata.recon.common.AppConstants;
import com.firstdata.recon.common.ReconAppException;
import com.firstdata.recon.util.BatchUtils;

public class GenericFileItemReader<T> extends FlatFileItemReader<T> implements ItemReader<T> {

  private static Logger log = LoggerFactory.getLogger(GenericFileItemReader.class);

  public GenericFileItemReader(final String fileName, final String loadType, final Range[] tokenizerRanges, final FieldSetMapper<T> fieldSetMapper,
      final int linesToSkip) {
    log.debug("Generic Item Reader : FileName: {}, loadType : {}, FieldSetMapper : {}", fileName, loadType, fieldSetMapper);
    Resource resource = new FileSystemResource(fileName);
    this.setResource(resource);
    DefaultLineMapper<T> lineMapper = new DefaultLineMapper<T>();
    lineMapper.setLineTokenizer(lineTokenizer(loadType, tokenizerRanges));
    lineMapper.setFieldSetMapper(fieldSetMapper);
    this.setLineMapper(lineMapper);
    this.setLinesToSkip(linesToSkip);
  }

  @BeforeStep
  public void beforeStep(StepExecution stepExecution) throws ReconAppException {
    String instId = BatchUtils.getInstitutionId(stepExecution);
    String jobName = BatchUtils.getJobName(stepExecution);
    Integer processDate = BatchUtils.getProcessDate(stepExecution);
    log.info("Generic Item Reader Before Step :  Inst Id : {}, Recon Name : {}, ProcessDate : {},", instId, jobName, processDate);
  }

  private static LineTokenizer lineTokenizer(String loadType, Range[] tokenizerRanges) {
    log.debug("Line Tokenizer :: init");
    LineTokenizer lineTokenizer;
    if (StringUtils.equals(loadType, AppConstants.FILE_TYPE_CSV)) {
      DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer(AppConstants.DELIMITER_COMMA);
      lineTokenizer = tokenizer;
    } else if (StringUtils.equals(loadType, AppConstants.FILE_TYPE_PIPE)) {
      DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer(AppConstants.DELIMITER_PIPE);
      lineTokenizer = tokenizer;
    } else {
      FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
      tokenizer.setColumns(tokenizerRanges);
      tokenizer.setStrict(false);
      lineTokenizer = tokenizer;
    }
    return lineTokenizer;
  }

}