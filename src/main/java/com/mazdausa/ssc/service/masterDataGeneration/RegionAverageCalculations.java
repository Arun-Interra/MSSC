package com.mazdausa.ssc.service.masterDataGeneration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscAltData;
import com.mazdausa.ssc.dao.SscSsoData;
import com.mazdausa.ssc.service.impl.RegionalDealersService;
import com.mazdausa.ssc.service.impl.SscAltDataServiceImpl;
import com.mazdausa.ssc.service.impl.SscSsoDataServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegionAverageCalculations {
	
	@Autowired
	private RegionalDealersService rgnDlrs;
	
	@Autowired
	private SscAltDataServiceImpl altServ;
	
	@Autowired
	private SscSsoDataServiceImpl ssoServ;
	
	
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
		
		log.debug("Region Average ALT VALUES -> " + Map_altRgnVsDlr);
		
		
		}
		catch(Exception e) {
			log.error("Unable to calculate the ALT region average - " + e);
		}
		
		return Map_altRgnVsDlr;
	}
	
	
public Map<String, Double> GetDealerRetentionRgnAvg() {
		
		Map<String, Double> Map_DealerRetentionRgnAvg = new HashMap<String, Double>();
		
		Map<String, Set<String>> RgnDealers = rgnDlrs.getRgnDealers();
		
		try {
		
		List<SscSsoData> ssoData = ssoServ.getSsoData(null); // null returns the data for all the dealers
		
		Predicate<SscSsoData> inMW = i -> RgnDealers.get("MW").contains(i.getDLR_CD());
		Predicate<SscSsoData> inNE = i -> RgnDealers.get("NE").contains(i.getDLR_CD());
		Predicate<SscSsoData> inGU = i -> RgnDealers.get("GU").contains(i.getDLR_CD());
		Predicate<SscSsoData> inPA = i -> RgnDealers.get("PA").contains(i.getDLR_CD());
		
		double MWAvg  = (ssoData.stream().filter(inMW).mapToDouble(ad -> ad.getSSO_RETENTION_PCT()).sum()) / (RgnDealers.get("MW").size());
		
		double NEAvg  = (ssoData.stream().filter(inNE).mapToDouble(ad -> ad.getSSO_RETENTION_PCT()).sum()) / (RgnDealers.get("NE").size());
		
		double GUAvg  = (ssoData.stream().filter(inGU).mapToDouble(ad -> ad.getSSO_RETENTION_PCT()).sum()) / (RgnDealers.get("GU").size());
	
		double PAAvg  = (ssoData.stream().filter(inPA).mapToDouble(ad -> ad.getSSO_RETENTION_PCT()).sum()) / (RgnDealers.get("PA").size());
		
		
		Map_DealerRetentionRgnAvg.put("MW", MWAvg);
		Map_DealerRetentionRgnAvg.put("NE", NEAvg);
		Map_DealerRetentionRgnAvg.put("GU", GUAvg);
		Map_DealerRetentionRgnAvg.put("PA", PAAvg);
		
		log.debug("Region Average SSO_RETENTION_PCT -> " + Map_DealerRetentionRgnAvg);
		
		
		}
		catch(Exception e) {
			log.error("Unable to calculate the Dealer Retention region average - " + e);
		}
		
		return Map_DealerRetentionRgnAvg;
	}

public Map<String, Double> GetROAmtRgnAvg() {
	
	Map<String, Double> Map_ROAmtRgnAvg = new HashMap<String, Double>();
	
	Map<String, Set<String>> RgnDealers = rgnDlrs.getRgnDealers();
	
	try {
	
	List<SscSsoData> ssoData = ssoServ.getSsoData(null); // null returns the data for all the dealers
	
	Predicate<SscSsoData> inMW = i -> RgnDealers.get("MW").contains(i.getDLR_CD());
	Predicate<SscSsoData> inNE = i -> RgnDealers.get("NE").contains(i.getDLR_CD());
	Predicate<SscSsoData> inGU = i -> RgnDealers.get("GU").contains(i.getDLR_CD());
	Predicate<SscSsoData> inPA = i -> RgnDealers.get("PA").contains(i.getDLR_CD());
	
	double MWAvg  = (ssoData.stream().filter(inMW).mapToDouble(ad -> ad.getAVG_RO_AMT()).sum()) / (RgnDealers.get("MW").size());
	
	double NEAvg  = (ssoData.stream().filter(inNE).mapToDouble(ad -> ad.getAVG_RO_AMT()).sum()) / (RgnDealers.get("NE").size());
	
	double GUAvg  = (ssoData.stream().filter(inGU).mapToDouble(ad -> ad.getAVG_RO_AMT()).sum()) / (RgnDealers.get("GU").size());

	double PAAvg  = (ssoData.stream().filter(inPA).mapToDouble(ad -> ad.getAVG_RO_AMT()).sum()) / (RgnDealers.get("PA").size());
	
	
	Map_ROAmtRgnAvg.put("MW", MWAvg);
	Map_ROAmtRgnAvg.put("NE", NEAvg);
	Map_ROAmtRgnAvg.put("GU", GUAvg);
	Map_ROAmtRgnAvg.put("PA", PAAvg);
	
	log.debug("Region Average SSO - RO Amount -> " + Map_ROAmtRgnAvg);
	
	
	}
	catch(Exception e) {
		log.error("Unable to calculate the SSO - RO Amount region average - " + e);
	}
	
	return Map_ROAmtRgnAvg;
}

}
