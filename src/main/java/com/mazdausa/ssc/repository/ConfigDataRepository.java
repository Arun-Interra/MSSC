package com.mazdausa.ssc.repository;

import java.util.List;

import com.mazdausa.ssc.dao.SscConfigData;

public interface ConfigDataRepository {
	List<SscConfigData> readAllDataFromDb();
	SscConfigData readSingleDataFromDb(String dlrCd);
	List<SscConfigData> readAllDataFromDbByMonth(String month, String year);
	SscConfigData updateAllDealerToDb(SscConfigData sscConfigData);
	List<SscConfigData> addMultipleDataToDb(List<SscConfigData> sscConfigDatas);

}
