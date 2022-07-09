package com.mazdausa.ssc.repository.impl;

import java.util.ArrayList;
import java.util.List;

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


}
