package com.mazdausa.ssc.service.masterDataGeneration;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscAltData;
import com.mazdausa.ssc.dao.SscReportingMasterData;
import com.mazdausa.ssc.service.impl.RegionalDealersService;
import com.mazdausa.ssc.service.impl.SscAltDataServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ALTCalc {
	
	@Autowired
	private SscAltDataServiceImpl altServ;
	
	@Autowired
	private ALTRegionCalc altRgn;
	
	@Autowired
	private RegionalDealersService rgnDlrs;

	public List<SscReportingMasterData> CalcAltData(List<SscReportingMasterData> masterData){
		
		Map<String, Double> Map_altRgnVsDlr = altRgn.GetAltRgnAvg();
		
		Map<String, Set<String>> RgnDealers = rgnDlrs.getRgnDealers();
		
		/*
		 * Calculation of ALT_DLR_ACT/ALT_RGN_ACT
		 */
		List<SscAltData> altData = altServ.getAltData(null); // null returns the data for all the dealers
		
		 altData.stream().forEach((ad) ->
		 {
			 SscReportingMasterData data = new SscReportingMasterData();
			 double dlrAlt = ad.getALT_VALUE();
			 if(dlrAlt == 0) {
				 data.setISSUE_OPPRT_AREA_SPLY("No Data");
			 }
		 if(RgnDealers.get("MW").contains(ad.getDLR_CD())) 
		 {
			 
			 double rgnAlt = Map_altRgnVsDlr.get("MW");
			 double dlrVsRgn = (dlrAlt/rgnAlt);
			 
			 data.setDLR_CD(ad.getDLR_CD());
			 data.setALT_DLR_ACT(dlrAlt);
			 data.setALT_RGN_ACT(rgnAlt);
			 data.setALT_DLR_VS_RGN_PCT(dlrVsRgn);
			 data.setRGN_CD("MW");
			
			 masterData.add(data);
		 }
		 else if(RgnDealers.get("NE").contains(ad.getDLR_CD())) 
		 {
			 double rgnAlt = Map_altRgnVsDlr.get("NE");
			 double dlrVsRgn =  (dlrAlt/rgnAlt);
			 
			 data.setDLR_CD(ad.getDLR_CD());
			 data.setALT_DLR_ACT(dlrAlt);
			 data.setALT_RGN_ACT(rgnAlt);
			 data.setALT_DLR_VS_RGN_PCT(dlrVsRgn);
			 data.setRGN_CD("NE");
			 
			 masterData.add(data);
		 }
		 else if(RgnDealers.get("GU").contains(ad.getDLR_CD())) 
		 {
			 double rgnAlt = Map_altRgnVsDlr.get("GU");
			 double dlrVsRgn =  (dlrAlt/rgnAlt);
			 
			 data.setDLR_CD(ad.getDLR_CD());
			 data.setALT_DLR_ACT(dlrAlt);
			 data.setALT_RGN_ACT(rgnAlt);
			 data.setALT_DLR_VS_RGN_PCT(dlrVsRgn);
			 data.setRGN_CD("GU");
			 
			 masterData.add(data);
		 }
		 else if(RgnDealers.get("PA").contains(ad.getDLR_CD())) 
		 {
			 double rgnAlt = Map_altRgnVsDlr.get("PA");
			 double dlrVsRgn =  (dlrAlt/rgnAlt);
			 
			 data.setDLR_CD(ad.getDLR_CD());
			 data.setALT_DLR_ACT(dlrAlt);
			 data.setALT_RGN_ACT(rgnAlt);
			 data.setALT_DLR_VS_RGN_PCT(dlrVsRgn);
			
			 masterData.add(data);
		 }
		});
		
		return masterData;
	}


}
