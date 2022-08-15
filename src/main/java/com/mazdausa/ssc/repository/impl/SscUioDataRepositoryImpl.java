package com.mazdausa.ssc.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.mazdausa.ssc.common.db.JdbcTemplateConfig;
import com.mazdausa.ssc.dao.SscUioData;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class SscUioDataRepositoryImpl {
	
	@Autowired
	private JdbcTemplateConfig jdbcTemp;
	
	final String UioDataDlrGetQuery = "SELECT * FROM MMAT.SSC_UIO_DATA WHERE DLR_CD = ?";
	
	final String UioDataGetQuery = "SELECT * FROM MMAT.SSC_UIO_DATA";

	
	public List<SscUioData> getUioData(String dlrCd) {
		List<SscUioData> Data = new ArrayList<SscUioData>();
		
		try {
			if(dlrCd != null)
				Data = jdbcTemp.jdbcTemplate().query(UioDataDlrGetQuery, new BeanPropertyRowMapper<>(SscUioData.class), dlrCd);
			else
				Data = jdbcTemp.jdbcTemplate().query(UioDataGetQuery, new BeanPropertyRowMapper<>(SscUioData.class));
			
			log.info(dlrCd,"Query result size" + Data.size());
		}
		catch(Exception e) {
			log.error("unbale to execute query", e);
		}
		
		return Data;
	}

}
