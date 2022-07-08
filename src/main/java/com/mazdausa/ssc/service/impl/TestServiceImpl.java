package com.mazdausa.ssc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.ssc.exception.SSCException;
import com.mazdausa.ssc.repository.TestRepository;
import com.mazdausa.ssc.service.TestService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TestServiceImpl implements TestService {

	@Autowired
	private TestRepository testRepo;

	@Override
	public Boolean acquireConnection(String dbKey) throws SSCException {
		log.debug(">> entering acquireConnection");
		return testRepo.acquireConnection(dbKey);
	}

}
