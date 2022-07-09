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
import com.mazdausa.ssc.service.impl.SscDfsBaseDataServiceImpl;
import com.mazdausa.ssc.service.impl.SscEmployeDataServiceImpl;
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
	
	@Autowired
	private SscDfsBaseDataServiceImpl dfsServ;
	
	@Autowired
	private SscEmployeDataServiceImpl empServ;
	
	
	@GetMapping(value = {"/alt", "/alt/{dlrCd}"})
	public GenericResponse getAltData(@PathVariable(required = false) String dlrCd) {
		if(dlrCd !=null)
			return GenericResponseWrapper.GenericResponseFunction.apply(
				altServ.getAltData(dlrCd),null);
		else
			return GenericResponseWrapper.GenericResponseFunction.apply(
					altServ.getAltData(null),null);
	}
	
	
	@GetMapping(value = {"/avg-visit","/avg-visit/{dlrCd}"})
	public GenericResponse getVisitData(@PathVariable(required = false) String dlrCd) {
		if(dlrCd != null)
			return GenericResponseWrapper.GenericResponseFunction.apply(
				avgVisitServ.getAvgVistData(dlrCd),null);
		else
			return GenericResponseWrapper.GenericResponseFunction.apply(
					avgVisitServ.getAvgVistData(null),null);
	}
	

	@GetMapping(value = {"/ro", "/ro/{dlrCd}"})
	public GenericResponse getRoData(@PathVariable(required = false) String dlrCd) {
		if(dlrCd != null)
			return GenericResponseWrapper.GenericResponseFunction.apply(
				roServ.getRoData(dlrCd),null);
		else
			return GenericResponseWrapper.GenericResponseFunction.apply(
					roServ.getRoData(null),null);
			
	}
	
	@GetMapping(value = {"/dfs", "/dfs/{dlrCd}"})
	public GenericResponse getDfsData(@PathVariable(required = false) String dlrCd) {
		if(dlrCd != null)
			return GenericResponseWrapper.GenericResponseFunction.apply(
				dfsServ.getDfsData(dlrCd),null);
		else
			return GenericResponseWrapper.GenericResponseFunction.apply(
					dfsServ.getDfsData(null),null);
	}
	
	@GetMapping(value = {"/employe", "/employe/{dlrCd}"})
	public GenericResponse getEmployeData(@PathVariable(required = false) String dlrCd) {
		if(dlrCd != null)
			return GenericResponseWrapper.GenericResponseFunction.apply(
				empServ.getEmployeData(dlrCd),null);
		else
			return GenericResponseWrapper.GenericResponseFunction.apply(
					empServ.getEmployeData(null),null);
	}
}
