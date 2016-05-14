package com.bizvpm.windchill.server.sqldb;

import java.sql.Connection;

public class DDB {

	private static DDB ddb;
	private ConnectionManager connMgr;

	public ConnectionManager getConnMgr() {
		return connMgr;
	}

	/**
	 * The constructor
	 */
	public DDB() {
		initConnection();
	}

	private void initConnection() {
		connMgr = ConnectionManager.getInstance();
	}

	public Connection getConnection(String dataSourceName) {
		return connMgr.getConnection(dataSourceName);
	}

	public Connection createConnection(String dataSourceName) {
		return connMgr.createConnection(dataSourceName);
	}

	public void freeConnection(String poolName, Connection connection) {
		connMgr.freeConnection(poolName, connection);
	}

	public void close() throws Exception {
		destoryConnection();
	}

	private void destoryConnection() {
		connMgr.release();
	}

	public static DDB getDefault() {
		if (ddb == null) {
			ddb = new DDB();
		}
		return ddb;
	}

}
