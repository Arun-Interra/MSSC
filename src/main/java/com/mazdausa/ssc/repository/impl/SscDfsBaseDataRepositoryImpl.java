package com.mazdausa.ssc.repository.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.mazdausa.ssc.common.db.JdbcTemplateConfig;
import com.mazdausa.ssc.dao.SscDfsBaseData;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class SscDfsBaseDataRepositoryImpl {

	@Autowired
	private JdbcTemplateConfig jdbcTemp;
	
	final String DfsDataDlrGetQuery = "SELECT * FROM MMAT.SSC_DFS_BASE_DATA WHERE DLR_CD = ?";
	
	final String DfsDataGetQuery = "SELECT * FROM MMAT.SSC_DFS_BASE_DATA";
	
	final String DfsRollTwlMnths = "SELECT DLR_CD, SUM(ACCT_VALU_QT) AS ACCT_VALU_QT FROM MMAT.SSC_DFS_BASE_DATA WHERE FNCL_STMT_CYC_DT BETWEEN ? AND ?  AND ACCT_POS_NO = ? GROUP BY DLR_CD";
	
	
	public List<SscDfsBaseData> getDfsData(String dlrCd) {
		List<SscDfsBaseData> Data = new ArrayList<SscDfsBaseData>();
		
		try {
			if(dlrCd != null)
				Data = jdbcTemp.jdbcTemplate().query(DfsDataDlrGetQuery,new BeanPropertyRowMapper<>(SscDfsBaseData.class), dlrCd);
			else
				Data = jdbcTemp.jdbcTemplate().query(DfsDataGetQuery, new BeanPropertyRowMapper<>(SscDfsBaseData.class));
		}
		catch(Exception e) {
			log.error("unable to execute quey", e);
		}
		
		return Data;
	}
	
	
	public List<SscDfsBaseData> getDfsRollTwlMnths(LocalDate startDate, LocalDate endDate, int ACCT_POS_NO) {
		List<SscDfsBaseData> Data = new ArrayList<SscDfsBaseData>();
		
		try {
				Data = jdbcTemp.jdbcTemplate().query(DfsRollTwlMnths, new BeanPropertyRowMapper<>(SscDfsBaseData.class), 
						Date.valueOf(startDate), 
						Date.valueOf(endDate),
						ACCT_POS_NO);
		}
		catch(Exception e) {
			log.error("unable to execute quey", e);
		}
		
		return Data;
	}

	
}
