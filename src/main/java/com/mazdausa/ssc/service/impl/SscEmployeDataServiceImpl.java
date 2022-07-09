package com.mazdausa.ssc.service.impl;

import java.util.List;

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

}
