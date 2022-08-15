package com.mazdausa.ssc.dao;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SscConfigData {

	public String TYPE;
	public String SUB_TYPE;
	public String KEY_CD;
	public String VALUE;
	public String DESCRIPTION;
	public String STATUS;
	public String CREATED_BY;
	public LocalDateTime CREATE_TM;
	public String LAST_UPDATED_BY;
	public LocalDateTime LAST_UPDATE_TM;
}
