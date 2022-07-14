package com.mazdausa.ssc.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.mazdausa.ssc.common.db.JdbcTemplateConfig;
import com.mazdausa.ssc.dao.SscConfigData;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class SscConfigDataRepositoryImpl {
	
	@Autowired
	private JdbcTemplateConfig jdbcTemp;
	
	final String ConfigDataDlrGetQuery = "SELECT * FROM MMAT.SSC_CONFIG_DATA WHERE DLR_CD = ?";
	
	final String ConfigDataGetQuery = "SELECT * FROM MMAT.SSC_CONFIG_DATA";

	
	public List<SscConfigData> getRoData(String dlrCd) {
		List<SscConfigData> Data = new ArrayList<SscConfigData>();
		
		try {
			if(dlrCd != null)
				Data = jdbcTemp.jdbcTemplate().query(ConfigDataDlrGetQuery, new BeanPropertyRowMapper<>(SscConfigData.class), dlrCd);
			else
				Data = jdbcTemp.jdbcTemplate().query(ConfigDataGetQuery, new BeanPropertyRowMapper<>(SscConfigData.class));
			
			log.info(dlrCd,"Query result size" + Data.size());
		}
		catch(Exception e) {
			log.error("unbale to execute query", e);
		}
		
		return Data;
	}

}
