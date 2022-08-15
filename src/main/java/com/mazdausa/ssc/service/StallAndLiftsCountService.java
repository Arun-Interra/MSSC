package com.mazdausa.ssc.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mazdausa.ssc.dao.SscFacilityData;


public interface StallAndLiftsCountService  {
	
	public List<SscFacilityData> getDataFromExcel(MultipartFile files);
	public List<SscFacilityData> insertUpdateMultipleDealer(List<SscFacilityData> sscFacilityDatas);
	public List<SscFacilityData> insertUpdateSingleDealer(SscFacilityData  sscFacilityData);
	public SscFacilityData updateAllDealers(SscFacilityData  sscFacilityData);
	public List<SscFacilityData> getDealerDetails(String  dlrCd);
//	public List<SscFacilityData> getAllDealersDetails();
	public List<SscFacilityData> getDealersDetailsByMonth(String month, String year);

}
