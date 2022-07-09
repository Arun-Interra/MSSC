package com.mazdausa.ssc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscRoData;
import com.mazdausa.ssc.repository.impl.SscRoDataRepositoryImpl;

@Service
public class SscRoDataServiceImpl {
	@Autowired
	private SscRoDataRepositoryImpl roRepo;

	public List<SscRoData> getRoData(){
		return roRepo.getRoData();
	}
}
