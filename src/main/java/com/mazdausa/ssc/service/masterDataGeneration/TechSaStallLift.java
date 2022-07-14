package com.mazdausa.ssc.service.masterDataGeneration;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscEmployeData;
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
	

	
	public List<SscReportingMasterData> CalctData(List<SscReportingMasterData> masterData){
		
		try {
			Map<String, Integer> SaCnt = empServ.getSaCnt(null);
			Map<String, Integer> TechCnt = empServ.getTechCnt(null);
			
			Map<String, Integer> StallCntAct = facServ.getStallsCnt(null);
		
			
			masterData.stream().forEach((md) ->{
				String dlr = md.getDLR_CD();
				if(TechCnt.containsKey(dlr)) {		//check the Map has the dealer -> indicates the dealers has data for technician
					
					md.setTECH_CNT_ACT(TechCnt.get(dlr));	//set the technician count
					md.setSTALLS_CNT_REQ(TechCnt.get(dlr));	//set the stall count as same as technician count
					
					if(StallCntAct.containsKey(dlr)) {				//check the Map has the dealer -> indicates the dealers has data for Facility
						
						md.setSTALLS_CNT_ACT(StallCntAct.get(dlr)); //set the stall count
						md.setSTALLS_USAGE_ACT_PCT((TechCnt.get(dlr) / StallCntAct.get(dlr)) * 100);  //set stall usage % Act -->(No of Tech Actual / No of Stall Actual)
						
						int stallToAdd = md.getSTALLS_CNT_REQ() - md.getSTALLS_CNT_ACT();   // calc stall to add --> (# required stall - # actual stall)
						if(stallToAdd > 0) {
							md.setSLTALLS_TO_ADD(stallToAdd);
						}
					}
					else {
						md.setSTALLS_CNT_ACT(0);
						md.setSTALLS_USAGE_ACT_PCT(0);
					}
				}
				else {
					md.setTECH_CNT_ACT(0);
					md.setSTALLS_CNT_REQ(0);
				}
				
				if(SaCnt.containsKey(dlr)) {
					md.setSA_CNT_ACT(SaCnt.get(dlr));
				}
				else {
					md.setSA_CNT_ACT(0);
				}
			});
			
		}
		catch(Exception e) {
			log.error("Unable to extract data from the employee table for Tech/SA/Stall data", e);
		}
		
		
		
		
		
		
		
		return masterData;
	}
}
