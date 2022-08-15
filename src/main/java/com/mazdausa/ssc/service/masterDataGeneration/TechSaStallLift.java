package com.mazdausa.ssc.service.masterDataGeneration;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscReportingMasterData;
import com.mazdausa.ssc.service.impl.SscEmployeDataServiceImpl;
import com.mazdausa.ssc.service.impl.SscFacilityDataServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TechSaStallLift {
	
	@Autowired
	private SscEmployeDataServiceImpl empServ;
	
	@Autowired
	private SscFacilityDataServiceImpl facServ;
	

	
	public Map<String, SscReportingMasterData> CalctData(Map<String, SscReportingMasterData> masterData){
		
		try {
			Map<String, Integer> SaCntAct = empServ.getSaCnt(null);
			Map<String, Integer> TechCntAct = empServ.getTechCnt(null);
			
			Map<String, Integer> StallCntAct = facServ.getStallsCnt(null);
		
			
			masterData.keySet().stream().forEach((dlr) ->{
				
				SscReportingMasterData data = masterData.get(dlr);
				
				if(TechCntAct.containsKey(dlr)) {		//check the Map has the dealer -> indicates the dealers has data for technician
					
					data.setTECH_CNT_ACT(TechCntAct.get(dlr));	//set the technician count
					data.setSTALLS_CNT_REQ(TechCntAct.get(dlr));	//set the stall count as same as technician count
					
					if(StallCntAct.containsKey(dlr)) {				//check the Map has the dealer -> indicates the dealers has data for Facility
						
						data.setSTALLS_CNT_ACT(StallCntAct.get(dlr)); //set the stall count
						if(StallCntAct.get(dlr) >0) {
							data.setSTALLS_USAGE_ACT_PCT((TechCntAct.get(dlr) / StallCntAct.get(dlr)) * 100);  //set stall usage % Act -->(No of Tech Actual / No of Stall Actual)
						}
						else {
							data.setSTALLS_USAGE_ACT_PCT(0);
						}
						
						int stallToAdd = data.getSTALLS_CNT_REQ() - data.getSTALLS_CNT_ACT();   // calc stall to add --> diff (#required stall - #actual stall)
						if(stallToAdd > 0) {
							data.setSLTALLS_TO_ADD(stallToAdd);
						}
					}
					else {
						data.setSTALLS_CNT_ACT(0);
						data.setSTALLS_USAGE_ACT_PCT(0);
						log.info("unable to find/retrive STALL data for : " +dlr);
					}
				}
				else {
					data.setTECH_CNT_ACT(0);
					data.setSTALLS_CNT_REQ(0);
					log.info("unable to find/retrive TECHNICIAN data for : " +dlr);
				}
				
				if(SaCntAct.containsKey(dlr)) {
					data.setSA_CNT_ACT(SaCntAct.get(dlr));
				}
				else {
					data.setSA_CNT_ACT(0);
					log.info("unable to find/retrive SERVICE_ADVISOR data for : " +dlr);
				}
			});
			
		}
		catch(Exception e) {
			log.error("Unable to extract data from the employee table for Tech/SA/Stall data", e);
		}	
		
		return masterData;
	}
}
