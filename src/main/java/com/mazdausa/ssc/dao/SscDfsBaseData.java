package com.mazdausa.ssc.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SscDfsBaseData {
	private LocalDate FNCL_STMT_CYC_DT;
	private String DLR_CD;
	private String DLR_FNCL_ACCT_CD;
	private int ACCT_POS_NO;
	private long ACCT_VALU_QT;
	private String CREATED_BY;
	private LocalDateTime CREATE_TM;
	private String LAST_UPDATED_BY;
	private LocalDateTime LAST_UPDATE_TM;

}
