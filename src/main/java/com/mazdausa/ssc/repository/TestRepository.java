package com.mazdausa.ssc.repository;

import com.mazdausa.ssc.exception.SSCException;

public interface TestRepository {

	public Boolean acquireConnection(String dbKey) throws SSCException;

}
