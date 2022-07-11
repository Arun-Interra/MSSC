package com.mazdausa.ssc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscConfigData;
import com.mazdausa.ssc.repository.impl.SscConfigDataRepositoryImpl;

@Service
public class SscConfigDataServiceImpl {
	
	@Autowired
	private SscConfigDataRepositoryImpl configRepo;
	
	public List<SscConfigData> getConfigData(String dlrCd){
		if(dlrCd != null)
			return configRepo.getRoData(dlrCd);
		else
			return configRepo.getRoData(null);
		
	}

}
