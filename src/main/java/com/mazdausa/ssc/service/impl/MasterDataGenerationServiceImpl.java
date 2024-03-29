package com.mazdausa.ssc.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscReportingMasterData;
import com.mazdausa.ssc.service.MasterDataGenerationService;
import com.mazdausa.ssc.service.masterDataGeneration.ALTCalculations;
import com.mazdausa.ssc.service.masterDataGeneration.ConfigData;
import com.mazdausa.ssc.service.masterDataGeneration.DemandSituationRelatedCalculations;
import com.mazdausa.ssc.service.masterDataGeneration.ProfitRelatedCalculations;
import com.mazdausa.ssc.service.masterDataGeneration.ServiceAdvisorRelatedCalculations;
import com.mazdausa.ssc.service.masterDataGeneration.TechnicianStallRelatedCalculations;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class MasterDataGenerationServiceImpl implements MasterDataGenerationService{
	
	@Autowired
	private RegionalDealersService rgnDlrs;
	
	@Autowired
	private ConfigData configData;
	
	@Autowired
	private ALTCalculations altCalc;
	
	@Autowired
	private TechnicianStallRelatedCalculations techServ;
	
	@Autowired
	private ServiceAdvisorRelatedCalculations  SaServ;
	
	@Autowired
	private ProfitRelatedCalculations profServ;
	
	@Autowired
	private DemandSituationRelatedCalculations dmdSitServ;
	

	//generate the base List of dealers master data
	public Map<String, SscReportingMasterData> getBaseMasterData(){
		Map<String, SscReportingMasterData> masterData = new HashMap<String, SscReportingMasterData>();
		Map<String, Set<String>> RgnDealers = rgnDlrs.getRgnDealers();
		try {
			
			log.info(("Dealers from these regions are populated using <common-service api> : " + RgnDealers.keySet()));
		
			RgnDealers.keySet().stream().forEach(RgnCd ->{
				RgnDealers.get(RgnCd).stream().forEach(dlrCd -> {
					SscReportingMasterData data = new SscReportingMasterData();
					
					data.setRGN_CD(RgnCd.toString());
					data.setDLR_CD(dlrCd);
					data.setCREATED_BY("App-Cald");
					data.setCREATE_TM((java.time.LocalDateTime.now()));
					
					masterData.put(dlrCd, data);				
				});
			});
		}
		catch(Exception e) {
			log.error("Unable to create base list of dealers master data ", e);
		}
		return masterData;
	}
		
//	@PostConstruct
	public Map<String, SscReportingMasterData> getMasterData(){
			Map<String, SscReportingMasterData> masterData = getBaseMasterData();
		try {
			masterData = configData.SetConfigData(masterData);
			masterData = altCalc.CalctAltData(masterData);
			masterData = techServ.CalctData(masterData);
			masterData = SaServ.CalctData(masterData);
			masterData = dmdSitServ.CalctData(masterData);
			masterData = profServ.CalctData(masterData);
			
		}
		catch(Exception e) {
			log.error("unable to calculate Data", e);
		}
		
		return masterData;
	}
}
