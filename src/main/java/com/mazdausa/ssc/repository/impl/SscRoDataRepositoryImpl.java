package com.mazdausa.ssc.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.mazdausa.ssc.common.db.JdbcTemplateConfig;
import com.mazdausa.ssc.dao.SscRoData;

@Repository
public class SscRoDataRepositoryImpl {
	
	@Autowired
	private JdbcTemplateConfig jdbcTemp;
	
	final String RoDataDlrGetQuery = "SELECT * FROM MMAT.SSC_RO_DATA WHERE DLR_CD = ?";
	
	final String RoDataGetQuery = "SELECT * FROM MMAT.SSC_RO_DATA";
	
	
	public SscRoData getDlrRoData(String dlrCd) {
		SscRoData Data = new SscRoData();
		
		try {
			Data = jdbcTemp.jdbcTemplate().queryForObject(RoDataDlrGetQuery, new BeanPropertyRowMapper<>(SscRoData.class), dlrCd);
		}
		catch(Exception e) {
			
		}
		
		return Data;
	}
	
	public List<SscRoData> getRoData() {
		List<SscRoData> Data = new ArrayList<SscRoData>();
		
		try {
			Data = jdbcTemp.jdbcTemplate().query(RoDataGetQuery, new BeanPropertyRowMapper<>(SscRoData.class));
		}
		catch(Exception e) {
			
		}
		
		return Data;
	}


}
