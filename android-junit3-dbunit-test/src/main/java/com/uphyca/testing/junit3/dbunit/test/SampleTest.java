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
package com.uphyca.testing.junit3.dbunit.test;

import java.sql.SQLException;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;

import android.content.Context;
import android.database.DatabaseUtils;

import com.uphyca.testing.junit3.dbunit.AndroidDBTestCase;

public class SampleTest extends AndroidDBTestCase {

    private static final String CREATE_TABLE_DDL = "DROP TABLE IF EXISTS sample;\n" + "CREATE TABLE sample(_id integer primary key autoincrement not null, name VARCHAR);\n";

    @Override
    protected IDataSet getDataSet() throws Exception {
        return getFlatXmlDataSetFromClasspathResrouce("sample_test.xml");
    }

    @Override
    protected String getDatabaseName() {
        return "sample.db";
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void onCreateDatabase(Context context) {
        DatabaseUtils.createDbFromSqlStatements(context, getDatabaseName(), 1, CREATE_TABLE_DDL);
    }

    public void testPreconditions() throws SQLException,
                                   Exception {

        // Fetch database data after executing your code
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("sample");
        
        // Load expected data from an XML dataset
        IDataSet expectedDataSet = getFlatXmlDataSetFromClasspathResrouce("sample_test.xml");
        ITable expectedTable = expectedDataSet.getTable("sample");

        // Assert actual database table match expected table
        Assertion.assertEquals(expectedTable, actualTable);
    }
}
