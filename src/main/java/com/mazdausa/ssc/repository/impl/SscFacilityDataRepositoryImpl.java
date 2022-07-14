package com.mazdausa.ssc.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.mazdausa.ssc.common.db.JdbcTemplateConfig;
import com.mazdausa.ssc.dao.SscFacilityData;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class SscFacilityDataRepositoryImpl {
	
	@Autowired
	private JdbcTemplateConfig jdbcTemp;

	final String getdlrFacilityData = "SELECT * FROM MMAT.SSC_FACILITY_DATA WHERE DLR_CD = ?";
	
	final String getFacilityData = "SELECT * FROM MMAT.SSC_FACILITY_DATA";
	
	
	public List<SscFacilityData> getFacilityData(String dlrCd){
			
			List<SscFacilityData> Data = new ArrayList<SscFacilityData>();
			
			try {
				if(dlrCd != null)
					Data = jdbcTemp.jdbcTemplate().query(getdlrFacilityData, new BeanPropertyRowMapper<>(SscFacilityData.class), dlrCd);
				else
					Data = jdbcTemp.jdbcTemplate().query(getFacilityData, new BeanPropertyRowMapper<>(SscFacilityData.class));
			}
			catch(Exception e) {
				log.error("unbale to execute query", e);
			}
			
			return Data;
		}
}
