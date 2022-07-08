package com.mazdausa.ssc.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 
 * @author ssamal
 *
 */
public interface AbstractController {

	/**
	 * This default method will be used to send ResponseEntity
	 * 
	 * @param <T>
	 * @param t
	 * @param httpStatus
	 * @return
	 */
	default <T> ResponseEntity<T> sendResponse(T t, HttpStatus httpStatus) {
		return new ResponseEntity<T>(t, httpStatus);
	}

}
