package com.mazdausa.ssc.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.mazdausa.ssc.common.db.JdbcTemplateConfig;
import com.mazdausa.ssc.dao.SscReportingMasterData;


@Repository
public class MasterDataRepositoryImpl{

	
	@Autowired
	private JdbcTemplateConfig jdbcTemp;
	
	
	final String MasterDataDlrGetQuery = "SELECT * FROM MMAT.SSC_REPORTING_MASTER_DATA WHERE DLR_CD = ?";
	
	final String MasterDataGetQuery = "SELECT * FROM MMAT.SSC_REPORTING_MASTER_DATA";
	
	
	public SscReportingMasterData getDlrMasterData(String dlrCd) {
	
		SscReportingMasterData rptMstData = new SscReportingMasterData();
		try {
			rptMstData = jdbcTemp.jdbcTemplate().queryForObject(MasterDataDlrGetQuery, new BeanPropertyRowMapper<>(SscReportingMasterData.class), dlrCd);
		}catch(Exception e) {
			System.out.println(e);
		}
		return rptMstData;
	}
	
	
	public List<SscReportingMasterData> getAllMasterData() {
	
		List<SscReportingMasterData> rptMstData = new ArrayList<SscReportingMasterData>();
		try {
			rptMstData = jdbcTemp.jdbcTemplate().query(MasterDataGetQuery, new BeanPropertyRowMapper<>(SscReportingMasterData.class));
		}catch(Exception e) {
			System.out.println(e);
		}
		return rptMstData;
	}
}
