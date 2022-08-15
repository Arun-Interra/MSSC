package com.mazdausa.ssc.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mazdausa.ssc.dao.SscUioData;

public interface ForecastUioService {
	public List<SscUioData> getDataFromExcel(MultipartFile files);
	public List<SscUioData> insertUpdateMultipleDealer(List<SscUioData> sscUioDatas);
	public List<SscUioData> insertUpdateSingleDealer(SscUioData  sscUioData);
	public List<SscUioData> getDealerDetails(String  dlrCd);
//	public List<SscFacilityData> getAllDealersDetails();
	public List<SscUioData> getDealersDetailsByMonth(String month, String year);

}
