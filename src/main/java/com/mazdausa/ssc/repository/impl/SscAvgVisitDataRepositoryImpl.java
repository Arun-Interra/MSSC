package com.mazdausa.ssc.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.mazdausa.ssc.common.db.JdbcTemplateConfig;
import com.mazdausa.ssc.dao.SscAvgVisitData;

@Repository
public class SscAvgVisitDataRepositoryImpl {

	@Autowired
	private JdbcTemplateConfig jdbcTemp;
	
	final String AvgVisitDataDlrGetQuery = "SELECT * FROM MMAT.SSC_AVG_VISIT_DATA WHERE DLR_CD = ?";
	
	final String AvgVisitDataGetQuery = "SELECT * FROM MMAT.SSC_AVG_VISIT_DATA";
	
	
	public List<SscAvgVisitData> getAvgVisitData(String dlrCd) {
		List<SscAvgVisitData> Data = new ArrayList<SscAvgVisitData>();
		
		try {
			if(dlrCd != null)
				Data = jdbcTemp.jdbcTemplate().query(AvgVisitDataDlrGetQuery, new BeanPropertyRowMapper<>(SscAvgVisitData.class), dlrCd);
			else
				Data = jdbcTemp.jdbcTemplate().query(AvgVisitDataGetQuery, new BeanPropertyRowMapper<>(SscAvgVisitData.class));
		}
		catch(Exception e) {
			
		}
		
		return Data;
	}
	
}
