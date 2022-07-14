package com.mazdausa.ssc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscSsoData;
import com.mazdausa.ssc.repository.impl.SscSsoDataRepositoryImpl;

@Service
public class SscSsoDataServiceImpl {
	
	@Autowired
	private SscSsoDataRepositoryImpl ssoRepo;
	
	public List<SscSsoData> getSsoData(String dlrCd){
		if(dlrCd != null)
			return ssoRepo.getRoData(dlrCd);
		else
			return ssoRepo.getRoData(null);
	}

}
