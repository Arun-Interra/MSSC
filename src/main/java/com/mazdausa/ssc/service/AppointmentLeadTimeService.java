package com.mazdausa.ssc.service;

import com.mazdausa.ssc.exception.SSCException;

public interface AppointmentLeadTimeService {
	
	Boolean acquireConnection(String dbKey) throws SSCException;
}
