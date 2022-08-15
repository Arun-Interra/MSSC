package com.mazdausa.ssc.repository;



import java.util.List;

import com.mazdausa.ssc.dao.SscFacilityData;

public interface StallAndLiftsRepository {
	SscFacilityData updateAllDealerToDb(SscFacilityData sscFacilityData);
	List<SscFacilityData> addMultipleDataToDb(List<SscFacilityData> sscFacilityDatas);
	SscFacilityData readSingleDataFromDb(String dlrCd);
	List<SscFacilityData> readAllDataFromDb();
	List<SscFacilityData> readAllDataFromDbByMonth(String month, String year);
}
