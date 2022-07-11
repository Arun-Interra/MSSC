package com.mazdausa.ssc.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegionalDealersService {
	
	@Autowired
	private CommonServiceRestConsumeImpl cmnServ;
	
	public Map<String, Set<String>> getRgnDealers(){
		
		Map<String, Set<String>> dlrs = new HashMap<String, Set<String>>();
		
		try {
						
			dlrs.put("GU", cmnServ.getRgnDlrs("GU").getDealers().stream().map(d ->d.getDlr_CD()).collect(Collectors.toSet()));
			dlrs.put("MW", cmnServ.getRgnDlrs("MW").getDealers().stream().map(d ->d.getDlr_CD()).collect(Collectors.toSet()));
			dlrs.put("NE", cmnServ.getRgnDlrs("NE").getDealers().stream().map(d ->d.getDlr_CD()).collect(Collectors.toSet()));
			dlrs.put("PA", cmnServ.getRgnDlrs("PA").getDealers().stream().map(d ->d.getDlr_CD()).collect(Collectors.toSet()));
			
		}catch(Exception e) {
			log.error("unable to retive dealers data from common service");
		}
		
		return dlrs;
	}

}
