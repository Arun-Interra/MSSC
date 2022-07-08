package com.mazdausa.ssc.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.mazdausa.ssc.common.db.JdbcTemplateConfig;
import com.mazdausa.ssc.dao.SscAltData;

@Repository
public class SscAltDataRepositoryImpl {
	
	
	@Autowired
	private JdbcTemplateConfig jdbcTemp;
	
	final String AltDataDlrGetQuery = "SELECT * FROM MMAT.SSC_ALT_DATA WHERE DLR_CD = ?";
	
	final String AltDataGetQuery = "SELECT * FROM MMAT.SSC_ALT_DATA";
	
	
	public SscAltData getDlrAltData(String dlrCd) {
		SscAltData altData = new SscAltData();
		
		try {
			altData = jdbcTemp.jdbcTemplate().queryForObject(AltDataDlrGetQuery, new BeanPropertyRowMapper<>(SscAltData.class), dlrCd);
		}
		catch(Exception e) {
			
		}
		
		return altData;
	}
	
	public List<SscAltData> getAltData() {
		List<SscAltData> altData = new ArrayList<SscAltData>();
		
		try {
			altData = jdbcTemp.jdbcTemplate().query(AltDataGetQuery, new BeanPropertyRowMapper<>(SscAltData.class));
		}
		catch(Exception e) {
			
		}
		
		return altData;
	}

}
