package com.mazdausa.ssc.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.mazdausa.ssc.common.db.JdbcTemplateConfig;
import com.mazdausa.ssc.dao.SscAvgVisitData;

@Repository
public class SscAvgVisitDataRepositoryImpl {

	@Autowired
	private JdbcTemplateConfig jdbcTemp;
	
	final String AltDataDlrGetQuery = "SELECT * FROM MMAT.SSC_AVG_VISIT_DATA WHERE DLR_CD = ?";
	
	
	
	public SscAvgVisitData getDlrAltData(String dlrCd) {
		SscAvgVisitData Data = new SscAvgVisitData();
		
		try {
			Data = jdbcTemp.jdbcTemplate().queryForObject(AltDataDlrGetQuery, new BeanPropertyRowMapper<>(SscAvgVisitData.class), dlrCd);
		}
		catch(Exception e) {
			
		}
		
		return Data;
	}
}
