package com.mazdausa.ssc.dao;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SscConfigData {

	private String TYPE;
	private String KEY_CD;
	private String VALUE;
	private String DESCRIPTION;
	private String STATUS;
	private String CREATED_BY;
	private LocalDateTime CREATE_TM;
	private String LAST_UPDATED_BY;
	private LocalDateTime LAST_UPDATE_TM;
}
