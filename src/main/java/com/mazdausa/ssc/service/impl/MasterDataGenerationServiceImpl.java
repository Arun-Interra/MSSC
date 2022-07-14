package com.mazdausa.ssc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscAltData;
import com.mazdausa.ssc.dao.SscReportingMasterData;
import com.mazdausa.ssc.service.MasterDataGenerationService;
import com.mazdausa.ssc.service.masterDataGeneration.ALTCalc;
import com.mazdausa.ssc.service.masterDataGeneration.ALTRegionCalc;
import com.mazdausa.ssc.service.masterDataGeneration.TechSaStallLift;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class MasterDataGenerationServiceImpl implements MasterDataGenerationService{
	
	@Autowired
	private ALTCalc altCalc;
	
	@Autowired
	private TechSaStallLift techServ;
	

	
	
	public List<SscReportingMasterData> masterData = new ArrayList<SscReportingMasterData>();
		
	
	public List<SscReportingMasterData> getMasterData(){
		
		try {
			
			masterData = altCalc.CalcAltData(masterData);
			masterData = techServ.CalctData(masterData);
			
		}
		catch(Exception e) {
			log.error("unable to calculate RO related Data", e);
		}
		
		return masterData;
	}
}
