package com.mazdausa.ssc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mazdausa.ssc.dao.GenericResponse;
import com.mazdausa.ssc.service.impl.GenericResponseWrapper;
import com.mazdausa.ssc.service.impl.SscAltDataServiceImpl;
import com.mazdausa.ssc.service.impl.SscAvgVisitDataServiceImpl;
import com.mazdausa.ssc.service.impl.SscRoDataServiceImpl;

@RestController
@RequestMapping("/base-data")
public class BaseDataController {
	
	@Autowired
	private SscAltDataServiceImpl altServ;
	
	@Autowired
	private SscRoDataServiceImpl roServ;
	
	@Autowired
	private SscAvgVisitDataServiceImpl avgVisitServ;
	
	@GetMapping("/alt/{dlrCD}")
	public GenericResponse getDlrAltData(@PathVariable String dlrCD) {
		return GenericResponseWrapper.GenericResponseFunction.apply(
				altServ.getDlrAltData(dlrCD),null);
	}

	@GetMapping("/alt")
	public GenericResponse getAltData() {
		return GenericResponseWrapper.GenericResponseFunction.apply(
				altServ.getAltData(),null);
	}
	
	@GetMapping("/avg-visit")
	public GenericResponse getAvgVisitData() {
		return GenericResponseWrapper.GenericResponseFunction.apply(
				avgVisitServ.getAvgVistData(),null);
	}
	
	@GetMapping("/ro")
	public GenericResponse getRoData() {
		return GenericResponseWrapper.GenericResponseFunction.apply(
				roServ.getRoData(),null);
	}
	
	@GetMapping("/ro/{dlrCd}")
	public GenericResponse getDlrRoData(@PathVariable String dlrCd) {
		return GenericResponseWrapper.GenericResponseFunction.apply(
				roServ.getDlrRoData(dlrCd),null);
	}
}
