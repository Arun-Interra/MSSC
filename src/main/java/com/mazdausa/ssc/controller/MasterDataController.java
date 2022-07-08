package com.mazdausa.ssc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mazdausa.ssc.dao.GenericResponse;
import com.mazdausa.ssc.dao.SscReportingMasterData;
import com.mazdausa.ssc.service.impl.GenericResponseWrapper;
import com.mazdausa.ssc.service.impl.MasterDataServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/master-data")
public class MasterDataController {
	
	@Autowired
	private MasterDataServiceImpl mstDataSrv;
	
	@ApiOperation(value = "SSC Reporting Master Data for all dealers",
			      notes = "GetMapping takes in no Argument, returnsa data for all dealers",
			      response = SscReportingMasterData.class)
	@GetMapping()
	public GenericResponse masterData(){
		
		return GenericResponseWrapper.GenericResponseFunction.apply(mstDataSrv.getAllMasterData(), null);
	}
	
	
	@ApiOperation(value = "SSC Reporting Master Data for given dealer",
				  notes = "GetMapping takes in dealer code(dlrCd), returnsa data for given dealers",
				  response = SscReportingMasterData.class)
	@GetMapping("/{dlrCd}")
	public GenericResponse masterData(@PathVariable String dlrCd){
		
		return GenericResponseWrapper.GenericResponseFunction.apply(mstDataSrv.getDlrMasterData(dlrCd), null);
	}
	
}
