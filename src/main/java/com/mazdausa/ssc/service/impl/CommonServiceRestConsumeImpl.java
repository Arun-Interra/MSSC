package com.mazdausa.ssc.service.impl;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.common.CommonServiceUrl;
import com.mazdausa.ssc.common.RestTemplateConfig;
import com.mazdausa.ssc.dao.Dealers;
import com.mazdausa.ssc.service.CommonServiceRestConsume;

@Service
public class CommonServiceRestConsumeImpl implements CommonServiceRestConsume{
	
	@Autowired
	private CommonServiceUrl cmnSrvEP;
	
	@Autowired
	private RestTemplateConfig rest;
	
	public ConcurrentHashMap<String, Dealers> dlrs = new ConcurrentHashMap<String, Dealers>();

	@Override
	public Dealers getRgnDlrs(String rgnCd) {
		
		String uri = cmnSrvEP.getRgn_dlr_url()+rgnCd;
		
		HttpHeaders header = rest.getHeader();
		
		
		ResponseEntity<Dealers> response = rest.getObject().exchange(uri, HttpMethod.GET, new HttpEntity<Dealers>(header), Dealers.class);
		
		dlrs.put(rgnCd, response.getBody());
		
		return dlrs.get(rgnCd);
	}

}
