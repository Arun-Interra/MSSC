package com.mazdausa.ssc.service.impl;


import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscEmployeData;
import com.mazdausa.ssc.repository.impl.SscEmployeDataRepositoryImpl;

@Service
public class SscEmployeDataServiceImpl {
	
	@Autowired
	private SscEmployeDataRepositoryImpl empRepo;
	
	public List<SscEmployeData> getEmployeData(String dlrCd){
		if(dlrCd != null)
			return empRepo.getEmpData(dlrCd);
		else
			return empRepo.getEmpData(null);
	}
	
	
	public Map<String, Integer> getTechCnt(String dlrCd) {
		if(dlrCd != null)
			return empRepo.getTechCnt(dlrCd).stream().collect(Collectors.toMap(SscEmployeData::getDLR_CD, SscEmployeData::getEMPLOYEE_CNT));
		else
			return empRepo.getTechCnt(null).stream().collect(Collectors.toMap(SscEmployeData::getDLR_CD, SscEmployeData::getEMPLOYEE_CNT));
	}
	
	public  Map<String, Integer> getSaCnt(String dlrCd) {
		if(dlrCd != null) {
	
			return empRepo.getSaCnt(dlrCd).stream().collect(Collectors.toMap(SscEmployeData::getDLR_CD, o -> o.getEMPLOYEE_CNT(), (o1, o2) -> o1 + o2));
			
		}
		else
			return empRepo.getSaCnt(null).stream().collect(Collectors.toMap(SscEmployeData::getDLR_CD, o -> o.getEMPLOYEE_CNT(), (o1, o2) -> o1 + o2));
	}

}
