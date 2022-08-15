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
import com.mazdausa.ssc.dao.SscConfigData;
import com.mazdausa.ssc.repository.ConfigDataRepository;

@Repository
public class ConfigDataRepositoryImpl extends AbstractRepository implements ConfigDataRepository {
	
	@Autowired
	private JdbcTemplateConfig jdbcTemp;

	final String ConfigDataDlrGetQuery = "SELECT * FROM MMAT.SSC_CONFIG_DATA WHERE DLR_CD = ?";
	
	final String ConfigDataGetQuery = "SELECT * FROM MMAT.SSC_CONFIG_DATA";

	@Override
	public List<SscConfigData> readAllDataFromDb() {
		List<SscConfigData> sscConfigData = new ArrayList<SscConfigData>();
		try {
			sscConfigData = jdbcTemp.jdbcTemplate().query(ConfigDataGetQuery, 
					new BeanPropertyRowMapper<>(SscConfigData.class));
		} catch(Exception e) {
			System.out.println(e);
		}
		return sscConfigData;
	}

	@Override
	public SscConfigData readSingleDataFromDb(String dlrCd) {
		SscConfigData sscConfigData = new SscConfigData();
		try {
			sscConfigData = jdbcTemp.jdbcTemplate().queryForObject(ConfigDataDlrGetQuery, 
					new BeanPropertyRowMapper<>(SscConfigData.class), dlrCd);
		}catch(Exception e) {
			System.out.println(e);
		}
		System.out.println(sscConfigData);  
		return sscConfigData;
	}

	@Override
	public List<SscConfigData> readAllDataFromDbByMonth(String month, String year) {
		List<SscConfigData> sscConfigData = new ArrayList<SscConfigData>();
		try {
			sscConfigData = jdbcTemp.jdbcTemplate().query("select * from MMAT.SSC_CONFIG_DATA where year= ? and month = ?", 
					new BeanPropertyRowMapper<>(SscConfigData.class), year, month);
		}catch(Exception e) {
			System.out.println(e);
		}
		return sscConfigData;
	}

	@Override
	public SscConfigData updateAllDealerToDb(SscConfigData sscConfigData) {
		int [] noOfupdatedRows=jdbcTemp.jdbcTemplate().batchUpdate("update MMAT.SSC_CONFIG_DATA set "
				+ "TYPE="+sscConfigData.getTYPE()+", SUB_TYPE="+sscConfigData.getSUB_TYPE()+", "
				+ "KEY_CD="+sscConfigData.getKEY_CD()+", "
				+ "VALUE="+sscConfigData.getVALUE()+", "
				+ "DESCRIPTION="+sscConfigData.getDESCRIPTION()+", "
				+ "STATUS="+sscConfigData.getSTATUS()+", "
				+ "CREATED_BY="+sscConfigData.getCREATED_BY()+", "
				+ "CREATE_TM=now()"+ ", "
				+ "LAST_UPDATED_BY="+sscConfigData.getLAST_UPDATED_BY()+", "
				+ "LAST_UPDATE_TM=now()");
		
		System.out.println("No of updated rows : " + noOfupdatedRows);
		return (noOfupdatedRows.length > 0 ) ? sscConfigData : null;
	}

	@Override
	public List<SscConfigData> addMultipleDataToDb(List<SscConfigData> sscConfigDatas) {
		String insertOrUpdateInstruction = null;
		insertOrUpdateInstruction = SqlFileReader.readTextFromFile("/sql/config_data_insert_or_update.sql");
		int[] insertedRows = jdbcTemp.jdbcTemplate().batchUpdate(insertOrUpdateInstruction, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				SscConfigData sscConfigData = sscConfigDatas.get(i);
				ps.setString(1, sscConfigData.getTYPE());
				ps.setString(2, sscConfigData.getSUB_TYPE());
				ps.setString(3, sscConfigData.getKEY_CD());
				ps.setString(4, sscConfigData.getVALUE());
				ps.setString(5, sscConfigData.getDESCRIPTION());
				ps.setString(6, sscConfigData.getSTATUS());
				
				ps.setString(7, sscConfigData.getCREATED_BY());
				// insertStatement.setTimestamp(9, dealerStallAndLiftCountDto.getCreateTime());
				ps.setString(8, sscConfigData.getLAST_UPDATED_BY());
				// insertStatement.setTimestamp(11,
				// dealerStallAndLiftCountDto.getLastUpdatedTime());

			}

			@Override
			public int getBatchSize() {
				return sscConfigDatas.size();
			}
		});
		return (insertedRows.length > 0 )? sscConfigDatas : null;
	}

}
