package com.firstdata.recon.mapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.firstdata.recon.model.PtlfLog;

public class PtlfLogMapper implements FieldSetMapper<PtlfLog>, ItemPreparedStatementSetter<PtlfLog> {

  private static Logger log = LoggerFactory.getLogger(PtlfLogMapper.class);

  public static final String INSERT_PTLF_LOG_1_SQL = "Insert into RC_{INSTID}_PTLF_LOG (MID,TID,AMT1,AMT2,TRNSCTN_DT,TRNSCTN_TM,APPRVL_CD,CARD_TYP,CARD_NBR,MSSG_TYP,TRNSCTN_CD,BATCH_NBR,INVOICE_NBR,SQNC_NBR,SESSION_ID,FT_NBR,RRN,DCC_AMT,DCC_CRRNCY,DCC_CNVRSN_RT,ORIGINATOR,PRCSSD_DT,FILE_TYPE)"
      + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '1')";
  public static final String INSERT_PTLF_LOG_2_SQL = "Insert into RC_{INSTID}_PTLF_LOG (MID,TID,AMT1,AMT2,TRNSCTN_DT,TRNSCTN_TM,APPRVL_CD,CARD_TYP,CARD_NBR,MSSG_TYP,TRNSCTN_CD,BATCH_NBR,INVOICE_NBR,SQNC_NBR,SESSION_ID,FT_NBR,RRN,DCC_AMT,DCC_CRRNCY,DCC_CNVRSN_RT,ORIGINATOR,PRCSSD_DT,FILE_TYPE)"
      + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '2')";

  public static final String DELETE_PTLF_LOG_1_SQL = "DELETE FROM RC_{INSTID}_PTLF_LOG WHERE PRCSSD_DT = ? AND FILE_TYPE='1'";
  public static final String DELETE_PTLF_LOG_2_SQL = "DELETE FROM RC_{INSTID}_PTLF_LOG WHERE PRCSSD_DT = ? AND FILE_TYPE='2'";

  @Override
  public void setValues(PtlfLog ptlfLog, PreparedStatement ps) throws SQLException {
    String amt1 = ptlfLog.getAmt1();
    amt1 = StringUtils.isEmpty(amt1) ? "0" : (amt1.substring(0, amt1.length() - 2) + "." + amt1.substring(amt1.length() - 2, amt1.length()));
    String amt2 = ptlfLog.getAmt2();
    amt2 = StringUtils.isEmpty(amt2) ? "0" : (amt2.substring(0, amt2.length() - 2) + "." + amt2.substring(amt2.length() - 2, amt2.length()));
    ps.setString(1, ptlfLog.getMid());
    ps.setString(2, ptlfLog.getTid());
    ps.setString(3, amt1);
    ps.setString(4, amt2);
    ps.setString(5, ptlfLog.getTrnsctnDt());
    ps.setString(6, ptlfLog.getTrnsctnTime());
    ps.setString(7, ptlfLog.getApprvlCode());
    ps.setString(8, ptlfLog.getCardTyp());
    ps.setString(9, ptlfLog.getCardNbr());
    ps.setString(10, ptlfLog.getMssgTyp());
    ps.setString(11, ptlfLog.getTrnsctnCd());
    ps.setString(12, ptlfLog.getBatchNbr());
    ps.setString(13, ptlfLog.getInvoiceNbr());
    ps.setString(14, ptlfLog.getSqncNbr());
    ps.setString(15, ptlfLog.getSessionId());
    ps.setString(16, ptlfLog.getFtNbr());
    ps.setString(17, ptlfLog.getRrn());
    ps.setString(18, ptlfLog.getDccAmt());
    ps.setString(19, ptlfLog.getDccCurrency());
    ps.setString(20, ptlfLog.getDccConvrsionRt());
    ps.setString(21, ptlfLog.getOriginator());
    ps.setInt(22, ptlfLog.getProcessedDate());

  }

  @Override
  public PtlfLog mapFieldSet(FieldSet fieldSet) throws BindException {
    PtlfLog ptlfLog = new PtlfLog();
    if (fieldSet.getFieldCount() < 21) {
      return ptlfLog;
    }
    try {

      ptlfLog.setMid(fieldSet.readString(0));
      ptlfLog.setTid(fieldSet.readString(1));
      ptlfLog.setAmt1(fieldSet.readString(2));
      ptlfLog.setAmt2(fieldSet.readString(3));
      ptlfLog.setTrnsctnDt(fieldSet.readString(4));
      ptlfLog.setTrnsctnTime(fieldSet.readString(5));
      ptlfLog.setApprvlCode(fieldSet.readString(6));
      ptlfLog.setCardTyp(fieldSet.readString(7));
      ptlfLog.setCardNbr(fieldSet.readString(8));
      ptlfLog.setMssgTyp(fieldSet.readString(9));
      ptlfLog.setTrnsctnCd(fieldSet.readString(10));
      ptlfLog.setBatchNbr(fieldSet.readString(11));
      ptlfLog.setInvoiceNbr(fieldSet.readString(12));
      ptlfLog.setSqncNbr(fieldSet.readString(13));
      ptlfLog.setSessionId(fieldSet.readString(14));
      ptlfLog.setFtNbr(fieldSet.readString(15));
      ptlfLog.setRrn(fieldSet.readString(16));
      ptlfLog.setDccAmt(fieldSet.readString(17));
      ptlfLog.setDccCurrency(fieldSet.readString(18));
      ptlfLog.setDccConvrsionRt(fieldSet.readString(19));
      ptlfLog.setOriginator(fieldSet.readString(20));

    } catch (Exception ex) {
      log.error("Field Mapper Exception : ", ex);
    }

    return ptlfLog;
  }
}
