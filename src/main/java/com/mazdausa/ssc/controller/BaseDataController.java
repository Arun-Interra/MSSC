package com.mazdausa.ssc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mazdausa.ssc.dao.GenericResponse;
import com.mazdausa.ssc.service.impl.GenericResponseWrapper;
import com.mazdausa.ssc.service.impl.RegionalDealersService;
import com.mazdausa.ssc.service.impl.SscAltDataServiceImpl;
import com.mazdausa.ssc.service.impl.SscAvgVisitDataServiceImpl;
import com.mazdausa.ssc.service.impl.SscConfigDataServiceImpl;
import com.mazdausa.ssc.service.impl.SscDfsBaseDataServiceImpl;
import com.mazdausa.ssc.service.impl.SscEmployeDataServiceImpl;
import com.mazdausa.ssc.service.impl.SscFacilityDataServiceImpl;
import com.mazdausa.ssc.service.impl.SscRoDataServiceImpl;
import com.mazdausa.ssc.service.impl.SscSsoDataServiceImpl;
import com.mazdausa.ssc.service.impl.SscUioDataServiceImpl;

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
	
	@Autowired
	private SscFacilityDataServiceImpl facilityServ;
	
	@Autowired
	private SscSsoDataServiceImpl ssoServ;
	
	@Autowired
	private SscUioDataServiceImpl uioServ;
	
	@Autowired
	private SscConfigDataServiceImpl configServ;
	
	@Autowired
	private RegionalDealersService rgnDlrServ;
	
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

	@GetMapping(value = {"/employe/Tech", "/employe/Tech/{dlrCd}"})
	public GenericResponse getTechCount(@PathVariable(required = false) String dlrCd) {
		if(dlrCd != null)
			return GenericResponseWrapper.GenericResponseFunction.apply(
				empServ.getTechCnt(dlrCd),null);
		else
			return GenericResponseWrapper.GenericResponseFunction.apply(
					empServ.getTechCnt(null),null);
	}
	
	@GetMapping(value = {"/employe/Sa", "/employe/Sa/{dlrCd}"})
	public GenericResponse getSaCount(@PathVariable(required = false) String dlrCd) {
		if(dlrCd != null)
			return GenericResponseWrapper.GenericResponseFunction.apply(
				empServ.getSaCnt(dlrCd),null);
		else
			return GenericResponseWrapper.GenericResponseFunction.apply(
					empServ.getSaCnt(null),null);
	}
	
	
	@GetMapping(value = {"/facility", "/facility/{dlrCd}"})
	public GenericResponse getFacilityData(@PathVariable(required = false) String dlrCd) {
		if(dlrCd != null)
			return GenericResponseWrapper.GenericResponseFunction.apply(
					facilityServ.getFacilityData(dlrCd),null);
		else
			return GenericResponseWrapper.GenericResponseFunction.apply(
					facilityServ.getFacilityData(null),null);
	}
	
	@GetMapping(value = {"/sso", "/sso/{dlrCd}"})
	public GenericResponse getSsoData(@PathVariable(required = false) String dlrCd) {
		if(dlrCd != null)
			return GenericResponseWrapper.GenericResponseFunction.apply(
					ssoServ.getSsoData(dlrCd),null);
		else
			return GenericResponseWrapper.GenericResponseFunction.apply(
					ssoServ.getSsoData(null),null);
	}
	
	@GetMapping(value = {"/uio", "/uio/{dlrCd}"})
	public GenericResponse getUioData(@PathVariable(required = false) String dlrCd) {
		if(dlrCd != null)
			return GenericResponseWrapper.GenericResponseFunction.apply(
					uioServ.getUioData(dlrCd),null);
		else
			return GenericResponseWrapper.GenericResponseFunction.apply(
					uioServ.getUioData(null),null);
	}
	
	
	@GetMapping(value = {"/config", "/config/{dlrCd}"})
	public GenericResponse getConfigData(@PathVariable(required = false) String dlrCd) {
		if(dlrCd != null)
			return GenericResponseWrapper.GenericResponseFunction.apply(
					configServ.getConfigData(dlrCd),null);
		else
			return GenericResponseWrapper.GenericResponseFunction.apply(
					configServ.getConfigData(null),null);
	}
	
	
	@GetMapping(value = {"/region-dlrs"})
	public GenericResponse getRegiondlrs() {

			return GenericResponseWrapper.GenericResponseFunction.apply(
					rgnDlrServ.getRgnDealers(),null);
	}
	
	
	
	@GetMapping(value = {"/Test", "/Test/{dlrCd}"})
	public GenericResponse getCWIYrRoData(@PathVariable(required = false) String dlrCd) {
		if(dlrCd != null)
			return GenericResponseWrapper.GenericResponseFunction.apply(
				roServ.getRollTwelveMonth(),null);
		else
			return GenericResponseWrapper.GenericResponseFunction.apply(
					roServ.getCWYrRoData(),null);
			
	}
	
	
}
