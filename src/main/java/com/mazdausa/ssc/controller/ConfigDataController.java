package com.mazdausa.ssc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mazdausa.ssc.dao.GenericResponse;
import com.mazdausa.ssc.dao.SscConfigData;
import com.mazdausa.ssc.service.ConfigDataService;
import com.mazdausa.ssc.service.impl.GenericResponseWrapper;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/configData")
public class ConfigDataController {
	
	@Autowired
	ConfigDataService configDataService;
	
	@ApiOperation(value = "SSC stallAndLifts Data for all dealers",
		      notes = "PostMapping takes in excel file as Argument, returns the inserted rows for the dealers",
		      response = SscConfigData.class)
	@PostMapping("/insertAndUpdateExcel")
	public GenericResponse insertAndUpdateExcel(@RequestParam("file") MultipartFile files) {
		ArrayList<SscConfigData> sscConfigData = 
				(ArrayList<SscConfigData>) configDataService.getDataFromExcel(files);
		return GenericResponseWrapper.GenericResponseFunction.apply(configDataService.insertUpdateMultipleDealer(sscConfigData), null);
	}
	
	@ApiOperation(value = "SSC config Data for all dealers",
		      notes = "PostMapping takes in SscFacilityData as Argument, returns the inserted rows for the dealers",
		      response = SscConfigData.class)
	@PostMapping("/insertAndUpdateSingleDealer")
	public GenericResponse insertAndUpdateSingleDealer(@RequestBody SscConfigData sscConfigData) {
		return GenericResponseWrapper.GenericResponseFunction.apply(configDataService.insertUpdateSingleDealer(sscConfigData), null);
	}
	
	@ApiOperation(value = "SSC config Data for all dealers",
		      notes = "PostMapping takes in List<SscFacilityData> as Argument, returns the inserted rows for the dealers",
		      response = SscConfigData.class)
	@PostMapping("/insertAndUpdateDealerList")
	public GenericResponse insertAndUpdateDealerList(@RequestBody List<SscConfigData> sscConfigDatas) {
		return GenericResponseWrapper.GenericResponseFunction.apply(configDataService.insertUpdateMultipleDealer(sscConfigDatas), null);
	}
	
	@ApiOperation(value = "SSC config Data for all dealers",
		      notes = "PostMapping takes in SscFacilityData as Argument, returns the inserted rows for the dealers",
		      response = SscConfigData.class)
	@PostMapping("/updateAllDealer")
	public GenericResponse updateAllDealer(@RequestBody SscConfigData sscConfigData) {
		return GenericResponseWrapper.GenericResponseFunction.apply(configDataService.updateAllDealers(sscConfigData), null);
	}
	
	@ApiOperation(value = "SSC config Data Data for all dealers",
		      notes = "GetMapping takes in month and year as Argument, returns the dealers information",
		      response = SscConfigData.class)
	@GetMapping("/dealers/{month}/{year}")
	public GenericResponse getDealersByMonth(@PathVariable("month") String month, @PathVariable("year") String year) {
		return GenericResponseWrapper.GenericResponseFunction.apply(configDataService.getDealersDetailsByMonth(month, year), null);
	}
	
	@ApiOperation(value = "SSC config Data for all dealers",
		      notes = "GetMapping takes in dlrCd as Argument, returns the the dealers inforamtion",
		      response = SscConfigData.class)
	@GetMapping("/dealers/{dlrCd}")
	public GenericResponse getDealerDetail(@PathVariable String dlrCd) {		
		System.out.println("Dealer code: "+dlrCd);
		return GenericResponseWrapper.GenericResponseFunction.apply(configDataService.getDealerDetails(dlrCd), null);
	}
}
