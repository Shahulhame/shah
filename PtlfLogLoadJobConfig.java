package com.firstdata.recon.job.load;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.firstdata.recon.common.AppConstants;
import com.firstdata.recon.common.JobParam;
import com.firstdata.recon.item.GenericFileItemReader;
import com.firstdata.recon.item.GenericItemProcessor;
import com.firstdata.recon.item.GenericJdbcItemWriter;
import com.firstdata.recon.mapper.PtlfLogMapper;
import com.firstdata.recon.model.PtlfLog;
import com.firstdata.recon.model.RcJobDetail;

@Configuration
public class PtlfLogLoadJobConfig extends BaseLoadJobConfig {

  private static final Logger log = LoggerFactory.getLogger(PtlfLogLoadJobConfig.class);

  private static final Range[] rangeTokenizers = new Range[] { new Range(1, 19), new Range(20, 35), new Range(36, 54), new Range(55, 73),
      new Range(74, 79), new Range(80, 85), new Range(86, 93), new Range(94, 95), new Range(96, 114), new Range(115, 118), new Range(119, 124),
      new Range(125, 127), new Range(128, 137), new Range(138, 149), new Range(150, 199), new Range(200, 214), new Range(215, 226),
      new Range(227, 238), new Range(239, 241), new Range(242, 249), new Range(250) };

  @Bean("loadPTLFLogJob")
  public Job loadPTLFLogJobConfig() {
    log.info("Load PTLF Log Job :: init");
    Flow loadPTLFLogMainFlow = new FlowBuilder<Flow>("loadPTLFLogMainFlow").from(tableCleanupStep).next(sftpDownloadStep).next(ptlfLogLoadStep())
        .end();
    Flow loadPTLFLogFileCleanupFlow = new FlowBuilder<Flow>("loadPTLFLogFileCleanupFlow").from(fileCleanupStep).end();
    return jobs.get("loadPTLFLogJob").listener(loadJobExecutionListener).start(loadPTLFLogMainFlow).on("*").to(loadPTLFLogFileCleanupFlow)
        .next(collectTableStatsStep).end().build();
  }

  @Bean("ptlfLogLoadStep")
  public Step ptlfLogLoadStep() {
    return steps.get("ptlfLogLoadJobStep").<PtlfLog, PtlfLog> chunk(chunkSize).reader(itemReader(null, null, null, null)).processor(itemProcessor())
        .writer(itemWriter(null, null, null)).build();
  }

  @Bean("ptlfLogLoadItemReader")
  @StepScope
  public FlatFileItemReader<PtlfLog> itemReader(@Value("#{jobParameters[instId]}") String instId, @Value("#{jobParameters[jobName]}") String jobName,
      @Value("#{jobParameters[processDate]}") Integer processDate, @Value("#{jobParameters[fileDate]}") Integer fileDate) {
    log.info("PTFL Log Item Reader :: Inst Id : {}, Job Name : {}, ProcessDate : {}, FileDate : {}", instId, jobName, processDate, fileDate);
    RcJobDetail rcJobDetail = rcJobDetailsDao.getRcJobDetailByJobName(instId, jobName);
    String fileName = rcJobDetail.getLocalPath() + reconHelperUtil.getUpdatedFileName(rcJobDetail.getFileName(), fileDate);
    return new GenericFileItemReader<PtlfLog>(fileName, AppConstants.FILE_TYPE_FIXED_LENGTH, rangeTokenizers, new PtlfLogMapper(),
        rcJobDetail.getLineToSkip());
  }

  @Bean("ptlfLogLoadItemProcessor")
  @StepScope
  public GenericItemProcessor<PtlfLog> itemProcessor() {
    log.info("PTLF LOG Item Processor :: init");
    return new GenericItemProcessor<PtlfLog>();
  }

  @Bean("ptlfLogLoadItemWriter")
  @StepScope
  public GenericJdbcItemWriter<PtlfLog> itemWriter(@Value("#{jobParameters[instId]}") String instId,
      @Value("#{jobParameters[jobName]}") String jobName, @Value("#{jobParameters[processDate]}") Integer processDate) {
    log.info("PTLF LOG Item Writer :: init");
    String sql = null;
    if (StringUtils.equals(JobParam.LOAD_PTLF_LOG_1.getValue(), jobName)) {
      sql = PtlfLogMapper.INSERT_PTLF_LOG_1_SQL;
    } else if (StringUtils.equals(JobParam.LOAD_PTLF_LOG_2.getValue(), jobName)) {
      sql = PtlfLogMapper.INSERT_PTLF_LOG_2_SQL;
    }
    return new GenericJdbcItemWriter<PtlfLog>(dataSource, sql, new PtlfLogMapper());
  }

}
