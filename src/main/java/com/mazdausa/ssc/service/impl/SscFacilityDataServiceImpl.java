package com.mazdausa.ssc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscFacilityData;
import com.mazdausa.ssc.repository.impl.SscFacilityDataRepositoryImpl;

import lombok.extern.slf4j.Slf4j;

@Service
public class SscFacilityDataServiceImpl {
	@Autowired
	private SscFacilityDataRepositoryImpl facilityRepo;
	
	public List<SscFacilityData> getFacilityData(String dlrCd){
		
		if(dlrCd != null)
			return facilityRepo.getFacilityData(dlrCd);
		else
			return facilityRepo.getFacilityData(null);
	}

}
