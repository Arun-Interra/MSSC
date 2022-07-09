package com.mazdausa.ssc.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.mazdausa.ssc.common.db.JdbcTemplateConfig;
import com.mazdausa.ssc.dao.SscAltData;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class SscAltDataRepositoryImpl {
	
	
	@Autowired
	private JdbcTemplateConfig jdbcTemp;
	
	final String AltDataDlrGetQuery = "SELECT * FROM MMAT.SSC_ALT_DATA WHERE DLR_CD = ?";
	
	final String AltDataGetQuery = "SELECT * FROM MMAT.SSC_ALT_DATA";
	
	
	public List<SscAltData> getAltData(String dlrCd) {
		List<SscAltData> altData = new ArrayList<SscAltData>();
		
		try {
			
			if(dlrCd != null) {
				altData = jdbcTemp.jdbcTemplate().query(AltDataDlrGetQuery, new BeanPropertyRowMapper<>(SscAltData.class), dlrCd);
			}
			
			else
				altData = jdbcTemp.jdbcTemplate().query(AltDataGetQuery, new BeanPropertyRowMapper<>(SscAltData.class));
		}
		catch(Exception e) {
			log.error("Unable to execute query", e);
		}
		
		return altData;
	}

}
