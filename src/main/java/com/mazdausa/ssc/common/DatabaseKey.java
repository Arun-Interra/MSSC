package com.mazdausa.ssc.common;

public enum DatabaseKey {

	udb("udb"),
	msscDB("mssc_db");

	private String dbKey;

	DatabaseKey(String dbKey) {
		this.dbKey = dbKey;
	}

	public String getDbKey() {
		return dbKey;
	}

}
