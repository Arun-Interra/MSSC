package com.mazdausa.ssc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.dao.SscAltData;
import com.mazdausa.ssc.service.MasterDataGenerationService;


@Service
public class MasterDataGenerationServiceImpl implements MasterDataGenerationService{
	
	@Autowired
	private RegionalDealersService rgnDlrs;
	
	@Autowired
	private SscAltDataServiceImpl altServ;

	@Override
	public Map<String, Float> AltRgnAct() {
		
		Map<String, Double> altRgnAct = new HashMap<String, Double>();
		
		Map<String, Set<String>> RgnDealers = rgnDlrs.getRgnDealers();
	
		
		List<SscAltData> altData = altServ.getAltData(null); // null returns the data for all the dealers
		
		
		double MWalt  = (altData.stream().filter(
				ad -> RgnDealers.get("MW").contains(ad.getDLR_CD())).mapToDouble(ad -> ad.getALT_VALUE()).sum()) / 
				(RgnDealers.get("MW").size());
		
		double NEalt  = (altData.stream().filter(
				ad -> RgnDealers.get("NE").contains(ad.getDLR_CD())).mapToDouble(ad -> ad.getALT_VALUE()).sum()) / 
				(RgnDealers.get("NE").size());
		
		double GUalt  = (altData.stream().filter(
				ad -> RgnDealers.get("GU").contains(ad.getDLR_CD())).mapToDouble(ad -> ad.getALT_VALUE()).sum()) / 
				(RgnDealers.get("GU").size());
		
		double PAalt  = (altData.stream().filter(
				ad -> RgnDealers.get("PA").contains(ad.getDLR_CD())).mapToDouble(ad -> ad.getALT_VALUE()).sum()) / 
				(RgnDealers.get("PA").size());
		
		altRgnAct.put("MW", MWalt);
		altRgnAct.put("NE", NEalt);
		altRgnAct.put("GU", GUalt);
		altRgnAct.put("PA", PAalt);
		
		
		System.out.println(altRgnAct);
		// TODO Auto-generated method stub
		return null;
	}

}
