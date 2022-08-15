package com.mazdausa.ssc.dao;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SscFacilityData {
	
	public int YEAR;
	public int MONTH;
	public String DLR_CD;
	public int STALLS_CNT;
	public int MMA_STALLS_CNT;
	public int LIFTS_CNT;
	public int MMA_LIFTS_CNT;
	public String CREATED_BY;
	public LocalDateTime  CREATE_TM;
	public String LAST_UPDATED_BY;
	public LocalDateTime LAST_UPDATE_TM;

}
