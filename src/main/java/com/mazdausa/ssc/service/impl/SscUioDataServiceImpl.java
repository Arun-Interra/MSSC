package com.mazdausa.ssc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscUioData;
import com.mazdausa.ssc.repository.impl.SscUioDataRepositoryImpl;

@Service
public class SscUioDataServiceImpl {

	@Autowired
	private SscUioDataRepositoryImpl uioRepo;
	
	
	public List<SscUioData> getUioData(String dlrCd){
		if(dlrCd != null)
			return uioRepo.getRoData(dlrCd);
		else
			return uioRepo.getRoData(null);
	}
}
