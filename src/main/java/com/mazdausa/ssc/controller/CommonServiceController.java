package com.mazdausa.ssc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mazdausa.ssc.dao.GenericResponse;
import com.mazdausa.ssc.service.impl.CommonServiceRestConsumeImpl;
import com.mazdausa.ssc.service.impl.GenericResponseWrapper;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/commonservice")
@ApiOperation(value = "This consumes the common services api")
public class CommonServiceController {

	@Autowired
	private CommonServiceRestConsumeImpl cmnSrvCon;
	
	@GetMapping("/region-dealers/{rgnCd}")
	@ApiOperation( value = "Get dealers from the given region", notes = "Endpoint takes in region-code (rgnCd) and gives dealer from the specific region")
	public GenericResponse rgnDealers(@PathVariable String rgnCd) {
		return GenericResponseWrapper.GenericResponseFunction.apply(cmnSrvCon.getRgnDlrs(rgnCd), null);
	}
}
