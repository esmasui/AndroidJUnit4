package com.uphyca.testing.junit3.dbunit;

import org.dbunit.JdbcDatabaseTester;

import android.content.Context;

public class AndroidSQLiteDatabaseTester extends JdbcDatabaseTester {

    private static final String DRIVER_CLASS = "SQLite.JDBCDriver";
    private static final String CONNECTION_URL_SCHEMA = "jdbc:sqlite:/";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    private static final String buildConnectionUrl(Context context,
                                                   String databaseName) {
        return CONNECTION_URL_SCHEMA + context.getDatabasePath(databaseName);
    }

    /**
     * Creates a new {@link JdbcDatabaseTester} using specific databaseName.
     * 
     * @param context
     * @param databaseName
     * @throws Exception
     */
    public AndroidSQLiteDatabaseTester(Context context,
                                       String databaseName) throws Exception {
        super(DRIVER_CLASS, buildConnectionUrl(context, databaseName), USERNAME, PASSWORD);
    }
}
