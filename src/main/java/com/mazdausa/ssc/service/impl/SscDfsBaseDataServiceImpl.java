package com.mazdausa.ssc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscDfsBaseData;
import com.mazdausa.ssc.repository.impl.SscDfsBaseDataRepositoryImpl;

@Service
public class SscDfsBaseDataServiceImpl {

	@Autowired
	private SscDfsBaseDataRepositoryImpl dfsRepo;
	
	public List<SscDfsBaseData> getDfsData(String dlrCd) {
		if(dlrCd != null)
			return dfsRepo.getDfsData(dlrCd);
		else
			return dfsRepo.getDfsData(null);
	}
	
}
