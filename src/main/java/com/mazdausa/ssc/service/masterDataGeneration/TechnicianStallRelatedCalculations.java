package com.mazdausa.ssc.service.masterDataGeneration;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscReportingMasterData;
import com.mazdausa.ssc.service.impl.SscEmployeDataServiceImpl;
import com.mazdausa.ssc.service.impl.SscFacilityDataServiceImpl;
import com.mazdausa.ssc.service.impl.SscRoDataServiceImpl;
import com.mazdausa.ssc.util.CalculationUtilsEnum;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TechnicianStallRelatedCalculations {
	
	@Autowired
	private SscEmployeDataServiceImpl empServ;
	
	@Autowired
	private SscFacilityDataServiceImpl facServ;
	
	@Autowired
	private SscRoDataServiceImpl RoServ;
	
	int TECH_CNT_ACT = 0;
	int STALLS_CNT_REQ = 0;
	int STALLS_TO_ADD = 0;
	double STALLS_USAGE_ACT_PCT = 0;
	int AVAIL_HOUR_PER_WEEK = 0;
	int FLAT_RATE_HOUR_PER_WEEK = 0;
	int RO_CIW_CNT_PER_WEEK_POT = 0;
	int RO_CIW_CNT_PER_YEAR_POT = 0;
	int RO_CIW_CNT_PER_YEAR_ACT = 0;
	int RO_CW_CNT_PER_YEAR_ACT = 0;
	double RO_CW_TO_CIW_PCT = 0;
	double RO_CIW_TO_CW_PCT = 0;
	int RO_CW_CNT_PER_YEAR_POT = 0;
	int TECH_TO_ADD = 0;
	double TECH_USAGE_ACT_PCT = 0;
	
	public Map<String, SscReportingMasterData> CalctData(Map<String, SscReportingMasterData> masterData){
		
		try {
			Map<String, Integer> TechCntAct = empServ.getTechCnt(null);
			
			Map<String, Integer> StallCntAct = facServ.getStallsCnt(null);
			
			Map<String, Integer> CWIYrRoData = RoServ.getCWIYrRoData();
			
			Map<String, Integer> CWYrRoData = RoServ.getCWYrRoData();
		
			
			masterData.keySet().stream().forEach((dlrCd) -> {
				
				SscReportingMasterData data = masterData.get(dlrCd);
				
				if(TechCntAct.containsKey(dlrCd) && StallCntAct.containsKey(dlrCd)) {		//check the Map has the dealer -> indicates the dealers has data for technician				
					
					TECH_CNT_ACT = TechCntAct.get(dlrCd);
					
					data.setTECH_CNT_ACT(TECH_CNT_ACT);
					
					data.setSTALLS_CNT_ACT(StallCntAct.get(dlrCd));
					
					data.setSTALLS_CNT_REQ(TECH_CNT_ACT);	//set the stall required count as same as technician count
					
					STALLS_USAGE_ACT_PCT = (StallCntAct.get(dlrCd) > 0) ? ((TECH_CNT_ACT / StallCntAct.get(dlrCd)) * 100) : (0) ;
					data.setSTALLS_USAGE_ACT_PCT ( STALLS_USAGE_ACT_PCT);
					
					STALLS_TO_ADD = data.getSTALLS_CNT_REQ() - data.getSTALLS_CNT_ACT() ; // calc stall to add --> diff (#required stall - #actual stall)
					
					data.setSLTALLS_TO_ADD( (STALLS_TO_ADD) > 0 ? STALLS_TO_ADD : 0);
					
					AVAIL_HOUR_PER_WEEK = (TECH_CNT_ACT * data.getAVAIL_HOUR_PER_TECH_PER_WEEK()); //AVAIL_HOUR_PER_WEEK -->(Technician count * AVAIL_HOUR_PER_TECH_PER_WEEK (From Config Value))
					
					data.setAVAIL_HOUR_PER_WEEK(AVAIL_HOUR_PER_WEEK);
					
					FLAT_RATE_HOUR_PER_WEEK = AVAIL_HOUR_PER_WEEK * (int) data.getPROFICIENCY_PCT();
					
					data.setFLAT_RATE_HOUR_PER_WEEK(FLAT_RATE_HOUR_PER_WEEK);
					
					RO_CIW_CNT_PER_WEEK_POT = (int) (FLAT_RATE_HOUR_PER_WEEK / data.getFLAT_RATE_HOUR_PER_RO());
					
					data.setRO_CIW_CNT_PER_WEEK_POT(RO_CIW_CNT_PER_WEEK_POT);
					
					RO_CIW_CNT_PER_YEAR_POT = (int) (RO_CIW_CNT_PER_WEEK_POT * (CalculationUtilsEnum.WEEK_PER_YEAR.getvalue()));
					
					data.setRO_CIW_CNT_PER_YEAR_POT(RO_CIW_CNT_PER_YEAR_POT);
					
					/////////////////////////////////////////////////////////s
					//RO-Related Data -> Rolling 12 month Total(CIW) and CW
					
					//check map contains the dealer code to avoid null pointer exception
					RO_CIW_CNT_PER_YEAR_ACT = CWIYrRoData.containsKey(dlrCd) ? CWIYrRoData.get(dlrCd) : 0;
					
					data.setRO_CIW_CNT_PER_YEAR_ACT(RO_CIW_CNT_PER_YEAR_ACT);
					
					//check map contains the dealer code to avoid null pointer exception
					RO_CW_CNT_PER_YEAR_ACT = CWYrRoData.containsKey(dlrCd) ? CWYrRoData.get(dlrCd) : 0;
					data.setRO_CW_CNT_PER_YEAR_ACT(RO_CW_CNT_PER_YEAR_ACT);  //Actual RO(C+W)
					
					//Check for the zero value to avoid the divide by zero error
					RO_CW_TO_CIW_PCT = RO_CIW_CNT_PER_YEAR_ACT != 0 ? 
							(RO_CW_CNT_PER_YEAR_ACT / RO_CIW_CNT_PER_YEAR_ACT): 0;
					data.setRO_CW_TO_CIW_PCT(RO_CW_TO_CIW_PCT);
					
					//check the value for zero to avoid divide by zero error
					RO_CIW_TO_CW_PCT = RO_CW_CNT_PER_YEAR_ACT > 0 ?
							RO_CIW_CNT_PER_YEAR_ACT / RO_CW_CNT_PER_YEAR_ACT : 0;
					data.setRO_CIW_TO_CW_PCT(RO_CIW_TO_CW_PCT);
					
					RO_CW_CNT_PER_YEAR_POT = (int) (RO_CIW_CNT_PER_YEAR_POT * RO_CW_TO_CIW_PCT);
					
					data.setRO_CW_CNT_PER_YEAR_POT(RO_CW_CNT_PER_YEAR_POT);
					
					//Check for the zero value to avoid the divide by zero error
					TECH_USAGE_ACT_PCT = RO_CW_CNT_PER_YEAR_POT > 0 ? CWYrRoData.get(dlrCd) / RO_CW_CNT_PER_YEAR_POT : 0;
					data.setTECH_USAGE_ACT_PCT(TECH_USAGE_ACT_PCT);					
					
					/*
					 * Calculate "# Tech  to add "  based on 
					 * if "[Actual] #RO(CP+W) /year" is greater than "[Potential] #RO (CP+W) /year"  
					 * then  ("[Actual] #RO (CP+W) /year" minus "[Potential] #RO (CP+W) /year") 
					 * divided by "[Potential] #RO (CP+W) /year" multiplied by "[Actual] # Tech".
					 */
					
					data.setTECH_TO_ADD(0); //setting initially to zero 
					
					if( (RO_CW_CNT_PER_YEAR_ACT > RO_CW_CNT_PER_YEAR_POT) && RO_CW_CNT_PER_YEAR_POT > 0 ) {
						
						TECH_TO_ADD = (RO_CW_CNT_PER_YEAR_ACT - RO_CW_CNT_PER_YEAR_POT) / (RO_CW_CNT_PER_YEAR_POT * TECH_CNT_ACT);
						
						data.setTECH_TO_ADD(TECH_TO_ADD);
					}
					

				}
				else {
					
					data.setTECH_CNT_ACT(TECH_CNT_ACT);
					data.setSTALLS_CNT_ACT(STALLS_TO_ADD);
					data.setSTALLS_CNT_REQ(STALLS_CNT_REQ);
					data.setSTALLS_USAGE_ACT_PCT(STALLS_USAGE_ACT_PCT);
					data.setSLTALLS_TO_ADD(STALLS_TO_ADD);
					data.setAVAIL_HOUR_PER_WEEK(AVAIL_HOUR_PER_WEEK);
					data.setFLAT_RATE_HOUR_PER_WEEK(FLAT_RATE_HOUR_PER_WEEK);
					data.setRO_CIW_CNT_PER_WEEK_POT(RO_CIW_CNT_PER_WEEK_POT);
					data.setRO_CIW_CNT_PER_YEAR_POT(RO_CIW_CNT_PER_YEAR_POT);
					data.setRO_CIW_CNT_PER_YEAR_ACT(RO_CIW_CNT_PER_YEAR_ACT);
					data.setRO_CW_CNT_PER_YEAR_ACT(RO_CW_CNT_PER_YEAR_ACT);
					data.setRO_CW_TO_CIW_PCT(RO_CW_TO_CIW_PCT);
					data.setRO_CW_CNT_PER_YEAR_POT(RO_CW_CNT_PER_YEAR_POT);
					data.setTECH_USAGE_ACT_PCT(TECH_USAGE_ACT_PCT);
					data.setTECH_TO_ADD(TECH_TO_ADD);
					log.info("unable to Retrive TECHNICIAN/STALL data for : " +dlrCd);
				}
				
			});
			
		}
		catch(Exception e) {
			log.error("Unable to calculate Technician/Stall related data - ", e);
		}	
		
		return masterData;
	}
}
