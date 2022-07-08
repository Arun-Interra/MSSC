package com.mazdausa.ssc.dao;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SscFacilityData {
	
	private int YEAR;
	private int MONTH;
	private String DLR_CD;
	private int STALLS_CNT;
	private int MMA_STALLS_CNT;
	private int LIFTS_CNT;
	private int MMA_LIFTS_CNT;
	private String CREATED_BY;
	private LocalDateTime  CREATE_TM;
	private String LAST_UPDATED_BY;
	private LocalDateTime LAST_UPDATE_TM;

}
