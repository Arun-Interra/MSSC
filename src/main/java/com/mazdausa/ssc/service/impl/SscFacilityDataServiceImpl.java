package com.mazdausa.ssc.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscFacilityData;
import com.mazdausa.ssc.repository.impl.SscFacilityDataRepositoryImpl;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SscFacilityDataServiceImpl {
	@Autowired
	private SscFacilityDataRepositoryImpl facilityRepo;
	
	public List<SscFacilityData> getFacilityData(String dlrCd){
		
		if(dlrCd != null)
			return facilityRepo.getFacilityData(dlrCd);
		else
			return facilityRepo.getFacilityData(null);
	}

	
public Map<String, Integer> getStallsCnt(String dlrCd){
		
		if(dlrCd != null)
			return facilityRepo.getFacilityData(dlrCd).stream().collect(Collectors.toMap(SscFacilityData::getDLR_CD, SscFacilityData::getSTALLS_CNT));
		else
			return facilityRepo.getFacilityData(null).stream().collect(Collectors.toMap(SscFacilityData::getDLR_CD, SscFacilityData::getSTALLS_CNT));
	}
}
