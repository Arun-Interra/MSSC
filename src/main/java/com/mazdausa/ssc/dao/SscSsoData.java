package com.mazdausa.ssc.dao;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SscSsoData {
	
	private int YEAR;
	private int MONTH;
	private String DLR_CD;
	private int SSO_UNIQUE_VISIT;
	private int SSO_UIO;
	private double SSO_RETENTION_PCT;
	private double YR3_RETENTION_PCT;
	private double MONTHLY_SALES_AMT;
	private int MONTHLY_RO_CNT;
	private double AVG_RO_AMT;
	private double PLUS2_VISIT_PCT;
	private double YR1_DLR_LOYALTY_PCT;
	private int ACTIVE_CUSTOMER_CNT;
	private String CREATED_BY;
	private LocalDateTime CREATE_TM;
	private String LAST_UPDATED_BY;
	private LocalDateTime LAST_UPDATE_TM;

}
