package com.mazdausa.ssc.dao;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SscAvgVisitData {

	private int YEAR;
	private int MONTH;
	private String DLR_CD;
	private String VEHICLE_AGE;
	private double AVG_VISIT_VAL;
	private String CREATED_BY;
	private LocalDateTime CREATE_TM;
	private String LAST_UPDATED_BY;
	private LocalDateTime LAST_UPDATE_TM;
}
