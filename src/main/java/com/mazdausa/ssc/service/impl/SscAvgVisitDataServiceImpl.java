package com.mazdausa.ssc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscAvgVisitData;
import com.mazdausa.ssc.repository.impl.SscAvgVisitDataRepositoryImpl;

@Service
public class SscAvgVisitDataServiceImpl {
	
	@Autowired
	private SscAvgVisitDataRepositoryImpl avgVistRepo;

	public List<SscAvgVisitData> getAvgVistData(String dlrCd){
		if(dlrCd != null)
			return avgVistRepo.getAvgVisitData(dlrCd);
		else
			return avgVistRepo.getAvgVisitData(null);
	}
	
}
