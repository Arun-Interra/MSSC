package com.mazdausa.ssc.repository;

import java.util.List;

import com.mazdausa.ssc.dao.SscUioData;

public interface ForecastUioRepository {
	List<SscUioData> addMultipleDataToDb(List<SscUioData> SscUioDatas);
	SscUioData readSingleDataFromDb(String dlrCd);
	List<SscUioData> readAllDataFromDb();
	List<SscUioData> readAllDataFromDbByMonth(String month, String year);

}
