package com.mazdausa.ssc.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.mazdausa.ssc.exception.SSCException;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class AbstractRepository {

	private final Map<String, DatabaseProperties> dbPropertyMap = new HashMap<String, DatabaseProperties>();

	@Autowired
	private Environment env;

	public Connection getConnection(String dbKey) throws SSCException {
		log.debug(">> Entering getConnection with key = {}", dbKey);
		Connection connection = null;
		try {
			DatabaseProperties dbProp = this.checkAndLoadDbProperty(dbKey);
			try {
				if (Objects.nonNull(dbProp.getDriver())) {
					Class.forName(dbProp.getDriver());
				}
				connection = DriverManager.getConnection(dbProp.getUrl(), dbProp.getUser(), dbProp.getPass());
			} catch (SQLException exp) {
				log.error("Error occurred on first try", exp);
				try {
					connection = DriverManager.getConnection(dbProp.getUrl(), dbProp.getUser(), dbProp.getPass());
				} catch (SQLException exp1) {
					log.error("Error occurred on re-try", exp1);
					throw exp1;
				}
			}
		} catch (Exception e) {
			throw new SSCException("Error occurred while getting connection", e);
		}
		return connection;
	}

	private DatabaseProperties checkAndLoadDbProperty(String dbKey) {
		if (dbPropertyMap.containsKey(dbKey)) {
			return dbPropertyMap.get(dbKey);
		} else {
			DatabaseProperties current = this.populateDbProperty(dbKey);
			dbPropertyMap.put(dbKey, current);
			return current;
		}
	}

	private DatabaseProperties populateDbProperty(String dbKey) {
		return DatabaseProperties.builder().driver(env.getProperty("database.jdbc." + dbKey + ".driver"))
				.url(env.getProperty("database.jdbc." + dbKey + ".url"))
				.user(env.getProperty("database.jdbc." + dbKey + ".user"))
				.pass(env.getProperty("database.jdbc." + dbKey + ".pass")).build();

	}

	public <T> void releaseResources(@SuppressWarnings("unchecked") T... resources) {
		for (T t : resources) {
			if (t instanceof ResultSet) {
				try {
					((ResultSet) t).close();
				} catch (Exception e) {
					log.error("Error occurred while closing ResultSet", e);
				}
			} else if (t instanceof Statement) {
				try {
					((Statement) t).close();
				} catch (Exception e) {
					log.error("Error occurred while closing Statement", e);
				}
			} else if (t instanceof Connection) {
				try {
					((Connection) t).close();
				} catch (Exception e) {
					log.error("Error occurred while closing Connection", e);
				}
			}
		}
	}
}
