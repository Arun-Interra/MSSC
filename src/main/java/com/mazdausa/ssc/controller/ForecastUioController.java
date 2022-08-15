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
import com.mazdausa.ssc.dao.SscUioData;
import com.mazdausa.ssc.service.ForecastUioService;
import com.mazdausa.ssc.service.impl.GenericResponseWrapper;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/forecastUio")
public class ForecastUioController {
	@Autowired
	private ForecastUioService forecastUioService;
	
	@ApiOperation(value = "SSC ForecastUIO Data for all dealers",
		      notes = "PostMapping takes in excel file as Argument, returns the inserted rows for the dealers",
		      response = SscUioData.class)
	@PostMapping("/insertAndUpdateExcel")
	public GenericResponse insertAndUpdateExcel(@RequestParam("file") MultipartFile files) {
		ArrayList<SscUioData> sscUioDatas = 
				(ArrayList<SscUioData>) forecastUioService.getDataFromExcel(files);
		return GenericResponseWrapper.GenericResponseFunction.apply(forecastUioService.insertUpdateMultipleDealer(sscUioDatas), null);
	}
	
	@ApiOperation(value = "SSC ForecastUIO Data for all dealers",
		      notes = "PostMapping takes in SscUioData as Argument, returns the inserted rows for the dealers",
		      response = SscUioData.class)
	@PostMapping("/insertAndUpdateSingleDealer")
	public GenericResponse insertAndUpdateSingleDealer(@RequestBody SscUioData sscUioData) {
		return GenericResponseWrapper.GenericResponseFunction.apply(forecastUioService.insertUpdateSingleDealer(sscUioData), null);
	}
	
	@ApiOperation(value = "SSC ForecastUIO Data for all dealers",
		      notes = "PostMapping takes in List<SscUioData> as Argument, returns the inserted rows for the dealers",
		      response = SscUioData.class)
	@PostMapping("/insertAndUpdateDealerList")
	public GenericResponse insertAndUpdateDealerList(@RequestBody List<SscUioData> sscUioDatas) {
		return GenericResponseWrapper.GenericResponseFunction.apply(forecastUioService.insertUpdateMultipleDealer(sscUioDatas), null);
	}
	
	@ApiOperation(value = "SSC ForecastUIO Data for all dealers",
		      notes = "GetMapping takes in dlrCd as Argument, returns dealer's information",
		      response = SscUioData.class)
	@GetMapping("/dealers/{dlrCd}")
	public GenericResponse getDealerDetail(@PathVariable String dlrCd) {		
		return GenericResponseWrapper.GenericResponseFunction.apply(forecastUioService.getDealerDetails(dlrCd), null);
	}
	
	@ApiOperation(value = "SSC ForecastUIO Data for all dealers",
		      notes = "GetMapping takes in month and year as Argument, returns the the dealers inforamtion",
		      response = SscUioData.class)
	@GetMapping("/dealers/{month}/{year}")
	public GenericResponse getDealersByMonth(@PathVariable("month") String month, @PathVariable("year") String year) {
		return GenericResponseWrapper.GenericResponseFunction.apply(forecastUioService.getDealersDetailsByMonth(month, year), null);
	}
}
