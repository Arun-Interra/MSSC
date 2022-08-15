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
import com.mazdausa.ssc.dao.SscFacilityData;
import com.mazdausa.ssc.service.StallAndLiftsCountService;
import com.mazdausa.ssc.service.impl.GenericResponseWrapper;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/stallAndLifts")
public class StallAndLiftsCountController {
	
	@Autowired
	private StallAndLiftsCountService stallAndLiftsCountService;
	
	@ApiOperation(value = "SSC stallAndLifts Data for all dealers",
		      notes = "PostMapping takes in excel file as Argument, returns the inserted rows for the dealers",
		      response = SscFacilityData.class)
	@PostMapping("/insertAndUpdateExcel")
	public GenericResponse insertAndUpdateExcel(@RequestParam("file") MultipartFile files) {
		ArrayList<SscFacilityData> sscFacilityDatas = 
				(ArrayList<SscFacilityData>) stallAndLiftsCountService.getDataFromExcel(files);
		return GenericResponseWrapper.GenericResponseFunction.apply(stallAndLiftsCountService.insertUpdateMultipleDealer(sscFacilityDatas), null);
	}
	
	@ApiOperation(value = "SSC stallAndLifts Data for all dealers",
		      notes = "PostMapping takes in SscFacilityData as Argument, returns the inserted rows for the dealers",
		      response = SscFacilityData.class)
	@PostMapping("/insertAndUpdateSingleDealer")
	public GenericResponse insertAndUpdateSingleDealer(@RequestBody SscFacilityData sscFacilityData) {
		return GenericResponseWrapper.GenericResponseFunction.apply(stallAndLiftsCountService.insertUpdateSingleDealer(sscFacilityData), null);
	}
	
	@ApiOperation(value = "SSC stallAndLifts Data for all dealers",
		      notes = "PostMapping takes in List<SscFacilityData> as Argument, returns the inserted rows for the dealers",
		      response = SscFacilityData.class)
	@PostMapping("/insertAndUpdateDealerList")
	public GenericResponse insertAndUpdateDealerList(@RequestBody List<SscFacilityData> sscFacilityDatas) {
		return GenericResponseWrapper.GenericResponseFunction.apply(stallAndLiftsCountService.insertUpdateMultipleDealer(sscFacilityDatas), null);
	}
	
	@ApiOperation(value = "SSC stallAndLifts Data for all dealers",
		      notes = "PostMapping takes in SscFacilityData as Argument, returns the inserted rows for the dealers",
		      response = SscFacilityData.class)
	@PostMapping("/updateAllDealer")
	public GenericResponse updateAllDealer(@RequestBody SscFacilityData sscFacilityData) {
		return GenericResponseWrapper.GenericResponseFunction.apply(stallAndLiftsCountService.updateAllDealers(sscFacilityData), null);
	}
	
	@ApiOperation(value = "SSC stallAndLifts Data for all dealers",
		      notes = "GetMapping takes in dlrCd as Argument, returns the the dealers inforamtion",
		      response = SscFacilityData.class)
	@GetMapping("/dealers/{dlrCd}")
	public GenericResponse getDealerDetail(@PathVariable String dlrCd) {		
		System.out.println("Dealer code: "+dlrCd);
		return GenericResponseWrapper.GenericResponseFunction.apply(stallAndLiftsCountService.getDealerDetails(dlrCd), null);
	}
	
	@ApiOperation(value = "SSC stallAndLifts Data for all dealers",
		      notes = "GetMapping takes in month and year as Argument, returns the dealers information",
		      response = SscFacilityData.class)
	@GetMapping("/dealers/{month}/{year}")
	public GenericResponse getDealersByMonth(@PathVariable("month") String month, @PathVariable("year") String year) {
		return GenericResponseWrapper.GenericResponseFunction.apply(stallAndLiftsCountService.getDealersDetailsByMonth(month, year), null);
	}
	

	

}
