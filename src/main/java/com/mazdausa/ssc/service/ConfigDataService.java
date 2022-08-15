package com.mazdausa.ssc.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mazdausa.ssc.dao.SscConfigData;

public interface ConfigDataService {
	
	public List<SscConfigData> getDealerDetails(String  dlrCd);
	
	public List<SscConfigData> getDataFromExcel(MultipartFile files);
	public List<SscConfigData> insertUpdateMultipleDealer(List<SscConfigData> sscConfigDatas);
	public List<SscConfigData> insertUpdateSingleDealer(SscConfigData  sscConfigData);
	public SscConfigData updateAllDealers(SscConfigData  sscConfigData);
//	public List<SscFacilityData> getAllDealersDetails();
	public List<SscConfigData> getDealersDetailsByMonth(String month, String year);

}
