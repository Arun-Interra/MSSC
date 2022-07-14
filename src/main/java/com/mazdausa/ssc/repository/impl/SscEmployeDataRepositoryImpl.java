package com.mazdausa.ssc.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.mazdausa.ssc.common.db.JdbcTemplateConfig;
import com.mazdausa.ssc.dao.SscEmployeData;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class SscEmployeDataRepositoryImpl {
	
	@Autowired
	private JdbcTemplateConfig jdbcTemp;
	

	final String EmployeDataDlrGetQuery = "SELECT * FROM MMAT.SSC_EMPLOYE_DATA WHERE DLR_CD = ?";
	
	final String EmployeDataGetQuery = "SELECT * FROM MMAT.SSC_EMPLOYE_DATA";
	
	final String TechCnt = "SELECT * FROM MMAT.SSC_EMPLOYE_DATA WHERE JOB_CD = 'D350'";
	
	final String SaCnt = "SELECT * FROM MMAT.SSC_EMPLOYE_DATA WHERE JOB_CD IN ('D303','D311')";
	
	final String DlrTechCnt = "SELECT * FROM MMAT.SSC_EMPLOYE_DATA WHERE DLR_CD = ? AND JOB_CD = 'D350'";
	
	final String DlrSaCnt = "SELECT * FROM MMAT.SSC_EMPLOYE_DATA WHERE DLR_CD = ? AND JOB_CD IN ('D303', 'D311')";

	
	public List<SscEmployeData> getEmpData(String dlrCd) {
		List<SscEmployeData> Data = new ArrayList<SscEmployeData>();
		
		try {
			if(dlrCd != null)
				Data = jdbcTemp.jdbcTemplate().query(EmployeDataDlrGetQuery, new BeanPropertyRowMapper<>(SscEmployeData.class), dlrCd);
			else
				Data = jdbcTemp.jdbcTemplate().query(EmployeDataGetQuery, new BeanPropertyRowMapper<>(SscEmployeData.class));
		}
		catch(Exception e) {
			log.error("unbale to execute query", e);
		}
		
		return Data;
	}
	
	public List<SscEmployeData> getTechCnt(String dlrCd) {
		List<SscEmployeData> Data = new ArrayList<SscEmployeData>();
		
		try {
			if(dlrCd != null) {
				Data = jdbcTemp.jdbcTemplate().query(DlrTechCnt, new BeanPropertyRowMapper<>(SscEmployeData.class), dlrCd);
			}
			else {
				Data = jdbcTemp.jdbcTemplate().query(TechCnt, new BeanPropertyRowMapper<>(SscEmployeData.class));
			}
		}
		catch(Exception e) {
			log.error("unbale to execute query", e);
		}
		
		return Data;
	}
	
	public List<SscEmployeData> getSaCnt(String dlrCd) {
		List<SscEmployeData> Data = new ArrayList<SscEmployeData>();
		
		try {
			if(dlrCd != null)
				Data = jdbcTemp.jdbcTemplate().query(DlrSaCnt, new BeanPropertyRowMapper<>(SscEmployeData.class), dlrCd);
			else
				Data = jdbcTemp.jdbcTemplate().query(SaCnt, new BeanPropertyRowMapper<>(SscEmployeData.class));
		}
		catch(Exception e) {
			log.error("unbale to execute query", e);
		}
		
		return Data;
	}
	
	

}
