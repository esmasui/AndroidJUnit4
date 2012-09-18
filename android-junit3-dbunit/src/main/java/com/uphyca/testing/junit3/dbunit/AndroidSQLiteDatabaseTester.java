package com.uphyca.testing.junit3.dbunit;

import org.dbunit.JdbcDatabaseTester;

public class AndroidSQLiteDatabaseTester extends JdbcDatabaseTester {

    private static final String DRIVER_CLASS = "SQLite.JDBCDriver";
    private static final String CONNECTION_URL_SCHEMA = "jdbc:sqlite:/";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    /**
     * Creates a new {@link JdbcDatabaseTester} using specific databasePath.
     * 
     * @param context
     * @param databasePath
     * @throws Exception
     */
    public AndroidSQLiteDatabaseTester(String databasePath) throws Exception {
        super(DRIVER_CLASS, CONNECTION_URL_SCHEMA + databasePath, USERNAME, PASSWORD);
    }
}
