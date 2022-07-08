package com.mazdausa.ssc.repository.impl;

import java.sql.Connection;

import org.springframework.stereotype.Repository;

import com.mazdausa.ssc.common.AbstractRepository;
import com.mazdausa.ssc.exception.SSCException;
import com.mazdausa.ssc.repository.TestRepository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class TestRepositoryImpl extends AbstractRepository implements TestRepository {

	@Override
	public Boolean acquireConnection(String dbKey) throws SSCException {
		try (Connection conn = super.getConnection(dbKey)) {
			return true;
		} catch (Exception e) {
			log.error("Error acquiring connection", e);
			throw new SSCException("", e);
		}
	}
}
