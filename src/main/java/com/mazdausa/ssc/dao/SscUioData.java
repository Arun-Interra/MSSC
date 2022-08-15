package com.mazdausa.ssc.dao;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SscUioData {

	public int YEAR;
	public int MONTH;
	public String DLR_CD;
	public String UIO_TYPE;
	public int UIO_VAL;
	public String CREATED_BY;
	public LocalDateTime CREATE_TM;
	public String LAST_UPDATED_BY;
	public LocalDateTime LAST_UPDATE_TM;
}
