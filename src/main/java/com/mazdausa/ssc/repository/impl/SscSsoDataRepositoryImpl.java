package com.mazdausa.ssc.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.mazdausa.ssc.common.db.JdbcTemplateConfig;
import com.mazdausa.ssc.dao.SscSsoData;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class SscSsoDataRepositoryImpl {
	
	@Autowired
	private JdbcTemplateConfig jdbcTemp;
	
	final String SsoDataDlrGetQuery = "SELECT * FROM MMAT.SSC_SSO_DATA WHERE DLR_CD = ?";
	
	final String SsoDataGetQuery = "SELECT * FROM MMAT.SSC_SSO_DATA";

	
	public List<SscSsoData> getRoData(String dlrCd) {
		List<SscSsoData> Data = new ArrayList<SscSsoData>();
		
		try {
			if(dlrCd != null)
				Data = jdbcTemp.jdbcTemplate().query(SsoDataDlrGetQuery, new BeanPropertyRowMapper<>(SscSsoData.class), dlrCd);
			else
				Data = jdbcTemp.jdbcTemplate().query(SsoDataGetQuery, new BeanPropertyRowMapper<>(SscSsoData.class));
		}
		catch(Exception e) {
			log.error("unbale to execute query", e);
		}
		
		return Data;
	}

}
