package com.mazdausa.ssc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscReportingMasterData;
import com.mazdausa.ssc.repository.impl.MasterDataRepositoryImpl;
import com.mazdausa.ssc.service.MasterDataService;

@Service
public class MasterDataServiceImpl implements MasterDataService{
	
	@Autowired
	private MasterDataRepositoryImpl mastDataRepo;
	

	
	public SscReportingMasterData getDlrMasterData(String dlrCd) {
			return mastDataRepo.getDlrMasterData(dlrCd);
	}
	
	public List<SscReportingMasterData> getAllMasterData(){
			return mastDataRepo.getAllMasterData();
	}

}
