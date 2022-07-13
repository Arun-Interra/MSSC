package com.mazdausa.ssc.service.masterDataGeneration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscAltData;
import com.mazdausa.ssc.service.impl.RegionalDealersService;
import com.mazdausa.ssc.service.impl.SscAltDataServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ALTRegionCalc {
	
	@Autowired
	private RegionalDealersService rgnDlrs;
	
	@Autowired
	private SscAltDataServiceImpl altServ;
	
	
	public Map<String, Double> GetAltRgnAvg() {
		
		Map<String, Double> Map_altRgnVsDlr = new HashMap<String, Double>();
		
		Map<String, Set<String>> RgnDealers = rgnDlrs.getRgnDealers();
		
		try {
		
		List<SscAltData> altData = altServ.getAltData(null); // null returns the data for all the dealers
		
		Predicate<SscAltData> inMW = i -> RgnDealers.get("MW").contains(i.getDLR_CD());
		Predicate<SscAltData> inNE = i -> RgnDealers.get("NE").contains(i.getDLR_CD());
		Predicate<SscAltData> inGU = i -> RgnDealers.get("GU").contains(i.getDLR_CD());
		Predicate<SscAltData> inPA = i -> RgnDealers.get("PA").contains(i.getDLR_CD());
		
		double MWalt  = (altData.stream().filter(inMW).mapToDouble(ad -> ad.getALT_VALUE()).sum()) / (RgnDealers.get("MW").size());
		
		double NEalt  = (altData.stream().filter(inNE).mapToDouble(ad -> ad.getALT_VALUE()).sum()) / (RgnDealers.get("NE").size());
		
		double GUalt  = (altData.stream().filter(inGU).mapToDouble(ad -> ad.getALT_VALUE()).sum()) / (RgnDealers.get("GU").size());
	
		double PAalt  = (altData.stream().filter(inPA).mapToDouble(ad -> ad.getALT_VALUE()).sum()) / (RgnDealers.get("PA").size());
		
		
		Map_altRgnVsDlr.put("MW", MWalt);
		Map_altRgnVsDlr.put("NE", NEalt);
		Map_altRgnVsDlr.put("GU", GUalt);
		Map_altRgnVsDlr.put("PA", PAalt);
		
		log.debug("Region AVG ALT VALUES -> " + Map_altRgnVsDlr);
		
		
		}
		catch(Exception e) {
			log.error("Unable to calculate the ALT region average");
		}
		
		return Map_altRgnVsDlr;
	}

}
