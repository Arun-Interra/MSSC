package com.mazdausa.ssc.service.masterDataGeneration;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscAltData;
import com.mazdausa.ssc.dao.SscReportingMasterData;
import com.mazdausa.ssc.service.impl.SscAltDataServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ALTCalculations {
	
	@Autowired
	private SscAltDataServiceImpl altServ;
	
	@Autowired
	private RegionAverageCalculations altRgn;
	
	double ALT_VALUE = 0;
	double ALT_RGN_ACT = 0;
	double ALT_DLR_VS_RGN_PCT = 0;
	

	public Map<String, SscReportingMasterData> CalctAltData(Map<String, SscReportingMasterData> masterData){
		
		Map<String, Double> Map_altRgnAvg = altRgn.GetAltRgnAvg();
		
		
		/*
		 * Calculation of ALT_DLR_ACT/ALT_RGN_ACT
		 */
		List<SscAltData> altData = altServ.getAltData(null); // null returns the data for all the dealers
		
		try {			
			altData.stream().forEach(ad ->{
				
				 String curDlr = ad.getDLR_CD();
				 if(masterData.containsKey(curDlr)) {
					 SscReportingMasterData data = masterData.get(curDlr);
					 
					 ALT_VALUE = ad.getALT_VALUE();
					 ALT_RGN_ACT = Map_altRgnAvg.get(data.getRGN_CD());

					 if(ALT_VALUE == 0) {
						 data.setISSUE_OPPRT_AREA_SPLY("No Data");
					 } 
					 
					 data.setALT_DLR_ACT(ALT_VALUE);
					 data.setALT_RGN_ACT(ALT_RGN_ACT);
					 
					 //check if the value is zero to avoid divide by zero error
					 ALT_DLR_VS_RGN_PCT = ALT_RGN_ACT > 0 ? (ALT_VALUE / ALT_RGN_ACT) : 0;
					 data.setALT_DLR_VS_RGN_PCT(ALT_DLR_VS_RGN_PCT);
				 
				 masterData.put(curDlr, data);
				 
				 } 
				 else {
					 log.info("Dealer not found in the regional dealer list <common-service api> : " +curDlr);
				 }
			});
			
			
		}
		catch(Exception e) {
			log.error("Unable to calculate the ALT data ", e);
		}
		
		return masterData;
	}
}
