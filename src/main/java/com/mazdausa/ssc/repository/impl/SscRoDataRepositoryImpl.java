package com.mazdausa.ssc.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.mazdausa.ssc.common.db.JdbcTemplateConfig;
import com.mazdausa.ssc.dao.SscRoData;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class SscRoDataRepositoryImpl {
	
	@Autowired
	private JdbcTemplateConfig jdbcTemp;
	
	final String RoDataDlrGetQuery = "SELECT * FROM MMAT.SSC_RO_DATA WHERE DLR_CD = ?";
	
	final String RoDataGetQuery = "SELECT * FROM MMAT.SSC_RO_DATA";
	
	final String RoTotalDataDatedQuery = "SELECT * FROM MMAT.SSC_RO_DATA WHERE MONTH BETWEEN ? AND ? AND DAY BETWEEN ? AND ? AND YEAR BETWEEN ? AND ?" ;

	public List<SscRoData> getRoData(String dlrCd) {
		List<SscRoData> Data = new ArrayList<SscRoData>();
		
		try {
			if(dlrCd != null)
				Data = jdbcTemp.jdbcTemplate().query(RoDataDlrGetQuery, new BeanPropertyRowMapper<>(SscRoData.class), dlrCd);
			else
				Data = jdbcTemp.jdbcTemplate().query(RoDataGetQuery, new BeanPropertyRowMapper<>(SscRoData.class));
		}
		catch(Exception e) {
			log.error("unbale to execute query", e);
		}
		
		return Data;
	}
	
	public List<SscRoData> getCPWIRoData(Map<String, Integer> dateMap) {
		List<SscRoData> Data = new ArrayList<SscRoData>();
		
		try {
			if(dateMap != null) {
				Data = jdbcTemp.jdbcTemplate().query(RoTotalDataDatedQuery, new BeanPropertyRowMapper<>(SscRoData.class), 
						dateMap.get("startMonth"), dateMap.get("endMonth"),
						dateMap.get("startDate"), dateMap.get("endDate"),
						dateMap.get("startYear"), dateMap.get("endYear"));
			}
			else
				Data = null;
		}
		catch(Exception e) {
			log.error("unbale to execute query", e);
		}
		
		return Data;
	}
	


}
