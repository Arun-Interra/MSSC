package com.mazdausa.ssc.repository.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.mazdausa.ssc.common.AbstractRepository;
import com.mazdausa.ssc.common.db.JdbcTemplateConfig;
import com.mazdausa.ssc.common.db.SqlFileReader;
import com.mazdausa.ssc.dao.SscFacilityData;
import com.mazdausa.ssc.repository.StallAndLiftsRepository;

@Repository
public class StallAndLiftsRepositoryImpl extends AbstractRepository implements StallAndLiftsRepository {

	@Autowired
	private JdbcTemplateConfig jdbcTemp;

	final String FacilityDataDlrGetQuery = "SELECT * FROM MMAT.SSC_FACILITY_DATA WHERE DLR_CD = ?";
	
	final String FacilityDataGetQuery = "SELECT * FROM MMAT.SSC_FACILITY_DATA";

	@Override
	public List<SscFacilityData> addMultipleDataToDb(List<SscFacilityData> sscFacilityDatas) {
		String insertOrUpdateInstruction = null;
		insertOrUpdateInstruction = SqlFileReader.readTextFromFile("/sql/stalls_and_lifts_insert_or_update.sql");
		int[] insertedRows=jdbcTemp.jdbcTemplate().batchUpdate(insertOrUpdateInstruction, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				SscFacilityData sscFacilityData = sscFacilityDatas.get(i);
				ps.setFloat(1, sscFacilityData.getYEAR());
				ps.setFloat(2, sscFacilityData.getMONTH());
				ps.setString(3, sscFacilityData.getDLR_CD());
				ps.setFloat(4, sscFacilityData.getSTALLS_CNT());
				ps.setFloat(5, sscFacilityData.getMMA_STALLS_CNT());
				ps.setFloat(6, sscFacilityData.getLIFTS_CNT());
				ps.setFloat(7, sscFacilityData.getMMA_LIFTS_CNT());
				ps.setString(8, sscFacilityData.getCREATED_BY());
				// insertStatement.setTimestamp(9, dealerStallAndLiftCountDto.getCreateTime());
				ps.setString(9, sscFacilityData.getLAST_UPDATED_BY());
				// insertStatement.setTimestamp(11,
				// dealerStallAndLiftCountDto.getLastUpdatedTime());

			}

			@Override
			public int getBatchSize() {
				return sscFacilityDatas.size();
			}
		});
		return (insertedRows.length>0)?sscFacilityDatas:null;

	}

	@Override
	public SscFacilityData readSingleDataFromDb(String dlrCd) {
		SscFacilityData sscFacilityData = new SscFacilityData();
		try {
			sscFacilityData = jdbcTemp.jdbcTemplate().queryForObject(FacilityDataDlrGetQuery, 
					new BeanPropertyRowMapper<>(SscFacilityData.class), dlrCd);
		}catch(Exception e) {
			System.out.println(e);
		}
		System.out.println(sscFacilityData);  
		return sscFacilityData;
	}

	@Override
	public List<SscFacilityData> readAllDataFromDb() {
		List<SscFacilityData> sscFacilityData = new ArrayList<SscFacilityData>();
		try {
			sscFacilityData = jdbcTemp.jdbcTemplate().query(FacilityDataGetQuery, 
					new BeanPropertyRowMapper<>(SscFacilityData.class));
		}catch(Exception e) {
			System.out.println(e);
		}
		return sscFacilityData;
	}

	@Override
	public SscFacilityData updateAllDealerToDb(SscFacilityData sscFacilityData) {
		
		int [] noOfupdatedRows=jdbcTemp.jdbcTemplate().batchUpdate("update MMAT.SSC_FACILITY_DATA set "
				+ "YEAR="+sscFacilityData.getYEAR()+", MONTH="+sscFacilityData.getMONTH()+", "
				+ "STALLS_CNT="+sscFacilityData.getSTALLS_CNT()+", "
				+ "MMA_STALLS_CNT="+sscFacilityData.getMMA_STALLS_CNT()+", "
				+ "LIFTS_CNT="+sscFacilityData.getLIFTS_CNT()+", "
				+ "MMA_LIFTS_CNT="+sscFacilityData.getMMA_LIFTS_CNT()+", "
				+ "CREATED_BY="+sscFacilityData.getCREATED_BY()+", "
				+ "LAST_UPDATED_BY="+sscFacilityData.getLAST_UPDATED_BY()+", "
				+ "LAST_UPDATE_TM=now()");
		
		System.out.println("No of updated rows : "+noOfupdatedRows);
		return (noOfupdatedRows.length>0)?sscFacilityData:null;
		
	}

	@Override
	public List<SscFacilityData> readAllDataFromDbByMonth(String month, String year) {
		List<SscFacilityData> sscFacilityData = new ArrayList<SscFacilityData>();
		try {
			sscFacilityData = jdbcTemp.jdbcTemplate().query("select * from MMAT.SSC_FACILITY_DATA where year= ? and month = ?", 
					new BeanPropertyRowMapper<>(SscFacilityData.class),year,month);
		}catch(Exception e) {
			System.out.println(e);
		}
		return sscFacilityData;
	}

}
