/*
 * Copyright (C) 2012 uPhyca Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uphyca.testing.junit3.dbunit;

import org.dbunit.JdbcDatabaseTester;

import android.content.Context;

public class AndroidSQLiteDatabaseTester extends JdbcDatabaseTester {

    private static final String DRIVER_CLASS = "SQLite.JDBCDriver";
    private static final String CONNECTION_URL_SCHEMA = "jdbc:sqlite:/";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    /**
     * Creates a new {@link JdbcDatabaseTester} using specific databaseName.
     * 
     * @param context
     * @param databasePath
     * @throws Exception
     */
    public AndroidSQLiteDatabaseTester(Context context,
                                       String databaseName) throws Exception {
        super(DRIVER_CLASS, buildConnectionUrl(context,
                                               databaseName), USERNAME, PASSWORD);
    }

    private static final String buildConnectionUrl(Context context,
                                                   String databaseName) {
        String dbPath = context.getDatabasePath(databaseName).getAbsolutePath();
        return CONNECTION_URL_SCHEMA + dbPath;
    }

}
