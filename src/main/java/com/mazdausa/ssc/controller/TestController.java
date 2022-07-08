package com.mazdausa.ssc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mazdausa.ssc.common.AbstractController;
import com.mazdausa.ssc.exception.SSCException;
import com.mazdausa.ssc.service.TestService;

/**
 *
 * @author ssamal
 *
 */
@RestController
public class TestController implements AbstractController {

	@Autowired
	private TestService testService;

	/**
	 *
	 * @return
	 */
	@GetMapping(value = "/say-hello")
	public ResponseEntity<String> sayHello() {
		return sendResponse("Wellcome......", HttpStatus.OK);
	}

	@GetMapping(value = "/acquireConnection")
	public ResponseEntity<Boolean> acquireConnection() throws SSCException {
		return sendResponse(testService.acquireConnection("udb_db01"), HttpStatus.OK);
	}

}