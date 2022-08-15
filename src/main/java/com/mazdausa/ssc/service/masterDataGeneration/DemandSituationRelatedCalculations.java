package com.mazdausa.ssc.service.masterDataGeneration;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscReportingMasterData;
import com.mazdausa.ssc.dao.SscSsoData;
import com.mazdausa.ssc.service.impl.SscSsoDataServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DemandSituationRelatedCalculations {
	
	@Autowired
	private SscSsoDataServiceImpl ssoDataServ;
	
	@Autowired
	private RegionAverageCalculations rgnAvgServ;
	
	double RGN_RETENTION_PCT = 0;
	double RGN_AVG_RO_AMT = 0;
	String ISSUE_OPPRT_AREA_DMND = "-";
	
	public Map<String, SscReportingMasterData> CalctData(Map<String, SscReportingMasterData> masterData){
		
		
		try {
			List<SscSsoData> ssodata = ssoDataServ.getSsoData(null);
			
			Map<String, Double> DealerRetentionRgnAvg = rgnAvgServ.GetDealerRetentionRgnAvg();
			
			Map<String, Double> ROAmtRgnAvg = rgnAvgServ.GetROAmtRgnAvg();
			
			ssodata.stream().forEach(sd -> {
				
			String curDlr = sd.getDLR_CD();
			
			if(masterData.containsKey(curDlr)) {
				
				SscReportingMasterData data = masterData.get(curDlr);
				
				String rgnCd = data.getRGN_CD(); 
				
				data.setUIO_ACT_CNT(sd.getSSO_UIO());
				
				data.setUNIQUE_VIN_CNT(sd.getSSO_UNIQUE_VISIT());
				
				data.setDLR_RETENSION_PCT(sd.getSSO_RETENTION_PCT());
				
				data.setDLR_AVG_RO_AMT(sd.getAVG_RO_AMT());
				
				data.setYRS3_DLR_RETENTION_PCT(sd.getYR3_RETENTION_PCT());
				
				//Check if the map has the data for the region-code, to avoid null pointer exception
				RGN_RETENTION_PCT = DealerRetentionRgnAvg.containsKey(rgnCd) ? DealerRetentionRgnAvg.get(rgnCd) : 0 ;
				data.setRGN_RETENTION_PCT(RGN_RETENTION_PCT);
				
				//Check if the map has the data for the region-code, to avoid null pointer exception
				RGN_AVG_RO_AMT = ROAmtRgnAvg.containsKey(rgnCd) ? ROAmtRgnAvg.get(rgnCd) : 0 ;
				data.setRGN_AVG_RO_AMT(RGN_AVG_RO_AMT);
				
				
				/*
				 * Calculate "[DEAMND] Issue/Opportunity Area (vs. Region Avg.)" as  
					If "Dealer Retention" < "[RGN Avg.] Dealer Retention" and "Avg RO" < "[RGN Avg.] Avg RO" then "Retention&$/RO"
					If "Dealer Retention" < "[RGN Avg.] Dealer Retention" then "Retention"
					If "Avg RO" < "[RGN Avg.] Avg RO" then "$/RO".

				 */
				
				
				
				if( sd.getSSO_RETENTION_PCT() < RGN_RETENTION_PCT ) {
					
					if((sd.getAVG_RO_AMT() < RGN_AVG_RO_AMT)) {
						
						ISSUE_OPPRT_AREA_DMND = "Retention&$/RO";
					}
					else {
						ISSUE_OPPRT_AREA_DMND = "Retention";
					}	
				}
				else if((sd.getAVG_RO_AMT() < RGN_AVG_RO_AMT)){
					ISSUE_OPPRT_AREA_DMND = "$/RO";
				}
				
				data.setISSUE_OPPRT_AREA_DMND(ISSUE_OPPRT_AREA_DMND);
				
			}
			
			else {
				 log.info("Dealer not found in the regional dealer list <common-service api> : " +curDlr);
			}
			});
			
		}
		catch(Exception e) {
			log.error("Unable to calculate Demand Situation - SSO Related Data "+ e);
		}
		
		
		
		return masterData;
	}

}
