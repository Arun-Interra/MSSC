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
public class ALTCalc {
	
	@Autowired
	private SscAltDataServiceImpl altServ;
	
	@Autowired
	private ALTRegionCalc altRgn;
	

	public Map<String, SscReportingMasterData> CalcAltData(Map<String, SscReportingMasterData> masterData){
		
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
					 
					 double dlrAlt = ad.getALT_VALUE();
					 double rgnAvg = Map_altRgnAvg.get(data.getRGN_CD());

					 if(dlrAlt == 0) {
						 data.setISSUE_OPPRT_AREA_SPLY("No Data");
					 } 
					 
					 data.setALT_DLR_ACT(dlrAlt);
					 data.setALT_RGN_ACT(rgnAvg);
					 data.setALT_DLR_VS_RGN_PCT(dlrAlt / rgnAvg);
				 
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
