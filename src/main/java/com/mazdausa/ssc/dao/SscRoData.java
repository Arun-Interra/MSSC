package com.mazdausa.ssc.dao;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SscRoData {

	private int YEAR;
	private int MONTH;
	private int DAY;
	private String DLR_CD;
	private int RO_CP_CNT;
	private int RO_INT_CNT;
	private int RO_WARR_CNT;
	private int RO_TOTAL_CNT;
	private String CREATED_BY;
	private LocalDateTime CREATE_TM;
	private String  LAST_UPDATED_BY;
	private LocalDateTime LAST_UPDATE_TM;
}
