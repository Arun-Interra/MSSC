package com.mazdausa.ssc.dao;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SscUioData {

	private int YEAR;
	private int MONTH;
	private String DLR_CD;
	private String UIO_TYPE;
	private int UIO_VAL;
	private String CREATED_BY;
	private LocalDateTime CREATE_TM;
	private String LAST_UPDATED_BY;
	private LocalDateTime LAST_UPDATE_TM;
}
