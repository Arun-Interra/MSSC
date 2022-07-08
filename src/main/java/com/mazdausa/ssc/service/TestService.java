package com.mazdausa.ssc.service;

import com.mazdausa.ssc.exception.SSCException;

public interface TestService {
	
	Boolean acquireConnection(String dbKey) throws SSCException;
}
