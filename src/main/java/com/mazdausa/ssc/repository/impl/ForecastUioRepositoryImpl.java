package com.mazdausa.ssc.repository.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.mazdausa.ssc.common.db.JdbcTemplateConfig;
import com.mazdausa.ssc.common.db.SqlFileReader;
import com.mazdausa.ssc.dao.SscUioData;
import com.mazdausa.ssc.repository.ForecastUioRepository;

@Repository
public class ForecastUioRepositoryImpl implements ForecastUioRepository {
	@Autowired
	private JdbcTemplateConfig jdbcTemp;

	final String UioDataDlrGetQuery = "SELECT * FROM MMAT.SSC_UIO_DATA WHERE DLR_CD = ?";
	
	final String UioDataGetQuery = "SELECT * FROM MMAT.SSC_UIO_DATA";

	@Override
	public List<SscUioData> addMultipleDataToDb(List<SscUioData> sscUioDatas) {
		String insertOrUpdateInstruction = null;
		insertOrUpdateInstruction = SqlFileReader.readTextFromFile("/sql/forecast_uio_read_or_update.sql");
		int[] insertedRows = jdbcTemp.jdbcTemplate().batchUpdate(insertOrUpdateInstruction, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				SscUioData sscUioData = sscUioDatas.get(i);
				ps.setFloat(1, sscUioData.getYEAR());
				ps.setFloat(2, sscUioData.getMONTH());
				ps.setString(3, sscUioData.getDLR_CD());
				ps.setString(4, sscUioData.getUIO_TYPE());
				ps.setFloat(5, sscUioData.getUIO_VAL());
				
				ps.setString(6, sscUioData.getCREATED_BY());
				// insertStatement.setTimestamp(9, dealerStallAndLiftCountDto.getCreateTime());
				ps.setString(7, sscUioData.getLAST_UPDATED_BY());
				// insertStatement.setTimestamp(11,
				// dealerStallAndLiftCountDto.getLastUpdatedTime());

			}

			@Override
			public int getBatchSize() {
				return sscUioDatas.size();
			}
		});
		return (insertedRows.length > 0)? sscUioDatas : null;
	}

	@Override
	public SscUioData readSingleDataFromDb(String dlrCd) {
		SscUioData sscUioData = new SscUioData();
		try {
			sscUioData = jdbcTemp.jdbcTemplate().queryForObject(UioDataDlrGetQuery, 
					new BeanPropertyRowMapper<>(SscUioData.class), dlrCd);
		}catch(Exception e) {
			System.out.println(e);
		}
		System.out.println(sscUioData);  
		return sscUioData;
	}

	@Override
	public List<SscUioData> readAllDataFromDb() {
		List<SscUioData> sscUioData = new ArrayList<SscUioData>();
		try {
			sscUioData = jdbcTemp.jdbcTemplate().query(UioDataGetQuery, 
					new BeanPropertyRowMapper<>(SscUioData.class));
		}catch(Exception e) {
			System.out.println(e);
		}
		return sscUioData;
	}

	@Override
	public List<SscUioData> readAllDataFromDbByMonth(String month, String year) {
		List<SscUioData> sscUioData = new ArrayList<SscUioData>();
		try {
			sscUioData = jdbcTemp.jdbcTemplate().query("select * from MMAT.SSC_UIO_DATA where year= ? and month = ?", 
					new BeanPropertyRowMapper<>(SscUioData.class), year, month);
		}catch(Exception e) {
			System.out.println(e);
		}
		return sscUioData;
	}

}
