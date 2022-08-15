package com.mazdausa.ssc.service.masterDataGeneration;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscReportingMasterData;
import com.mazdausa.ssc.service.impl.SscRoDataServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ROCalc {
	
	@Autowired
	private SscRoDataServiceImpl RoServ;
	
	public Map<String, SscReportingMasterData> CalcRoData(Map<String, SscReportingMasterData> masterData){
		
		Map<String, Integer> CWIYrRoData = RoServ.getCWIYrRoData();
		
		Map<String, Integer> CWYrRoData = RoServ.getCWYrRoData();
		
		try {
			
			masterData.keySet().stream().forEach(d ->{
				
				SscReportingMasterData data = masterData.get(d);
				
				String dlrCd = data.getDLR_CD();
				
				if(CWIYrRoData.containsKey(dlrCd) && CWYrRoData.containsKey(dlrCd)) {
					
					data.setRO_CIW_CNT_PER_YEAR_ACT(CWIYrRoData.get(dlrCd));
					data.setRO_CW_CNT_PER_YEAR_ACT(CWYrRoData.get(dlrCd));
					
					data.setRO_CIW_TO_CW_PCT( CWYrRoData.get(dlrCd) / CWIYrRoData.get(dlrCd));
				}
				else {
					data.setRO_CIW_CNT_PER_YEAR_ACT(-999); //Setting -999 for testing
					data.setRO_CW_CNT_PER_YEAR_ACT(-999); //Setting -999 for testing
				}
				
			});
			
		}
		catch(Exception e) {
		
			log.error("Unable to calculate the yearly RO data", e);
		}
		
		return masterData;
	}
}
