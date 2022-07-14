package com.mazdausa.ssc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscAltData;
import com.mazdausa.ssc.repository.impl.SscAltDataRepositoryImpl;

@Service
public class SscAltDataServiceImpl {
	
	@Autowired
	private SscAltDataRepositoryImpl altDataRepo;
	
//	public List<SscAltData> getDlrAltData(String dlrCd) {
//		return altDataRepo.getAltData(dlrCd);
//	}
//
//	public List<SscAltData> getAltData() {
//		return altDataRepo.getAltData(null);
//	}
	
	public List<SscAltData> getAltData(String dlrCd) {
		if(dlrCd != null )
			return altDataRepo.getAltData(dlrCd);
		else
			return altDataRepo.getAltData(null);
		
	}
}
