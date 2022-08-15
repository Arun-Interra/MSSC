package com.mazdausa.ssc.service.masterDataGeneration;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscReportingMasterData;
import com.mazdausa.ssc.service.impl.SscDfsBaseDataServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProfitRelatedCalculations {
	
	@Autowired
	private SscDfsBaseDataServiceImpl dfsBaseDataServ;
	
	long MAZ_LABOR_GP_R12_AMT = 0;
	long MAZ_LABOR_GP_R12_PER_STALL_AMT = 0;
	
public Map<String, SscReportingMasterData> CalctData(Map<String, SscReportingMasterData> masterData){
		
		try {
			
			Map<String, Long> dfsData = dfsBaseDataServ.getDfsRollTwlMnths();
			
			masterData.keySet().stream().forEach((dlr) -> {
				
				SscReportingMasterData data = masterData.get(dlr);
				
				//Check the Map for the dealerCode to avoid null pointer exception
				MAZ_LABOR_GP_R12_AMT = dfsData.containsKey(dlr) ? dfsData.get(dlr) : 0 ;
				
				data.setMAZ_LABOR_GP_R12_AMT(MAZ_LABOR_GP_R12_AMT);
				
				MAZ_LABOR_GP_R12_PER_STALL_AMT = data.getSTALLS_CNT_ACT() > 0 ? 
						MAZ_LABOR_GP_R12_AMT/data.getSTALLS_CNT_ACT() : 0 ;
				
				data.setMAZ_LABOR_GP_R12_PER_STALL_AMT(MAZ_LABOR_GP_R12_PER_STALL_AMT);
				
				
			});
			
		}
		catch(Exception e) {
			log.error("Unable to calculate profit - DFS_DATA - " + e);
		}
		
		return masterData;
	}
}
