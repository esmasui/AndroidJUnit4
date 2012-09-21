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

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.operation.DatabaseOperation;

import android.content.ContentValues;
import android.content.Context;

import com.uphyca.testing.junit3.dbunit.ProviderDBTestCase;

public class ProviderDBTestCaseTest extends ProviderDBTestCase<TinyContentProvider> {

    public ProviderDBTestCaseTest() {
        super(TinyContentProvider.class, TinyContentProvider.AUTHORITY);
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return getFlatXmlDataSetFromClasspathResrouce("provider_db_empty.xml");
    }

    @Override
    protected String getDatabaseName() {
        return TinySQLiteOpenHelper.DATABASE_NAME;
    }

    @Override
    protected void onCreateDatabase(Context context) {
        //Call SQLiteOpenHelper#getWritableDatabase() for create database.
        new TinySQLiteOpenHelper(context).getWritableDatabase().close();
    }

    public void testPreconditions() {
        assertNotNull(getProvider());
    }

    public void testInsert() throws Exception {

        ContentValues values = new ContentValues();
        values.put("name",
                   "foo");
        getProvider().insert(TinyContentProvider.CONTENT_URI,
                             values);

        // Fetch database data after executing your code
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("tiny");

        // Load expected data from an XML dataset
        IDataSet expectedDataSet = getFlatXmlDataSetFromClasspathResrouce("provider_db_after_insert.xml");
        ITable expectedTable = expectedDataSet.getTable("tiny");

        // Assert actual database table match expected table
        Assertion.assertEquals(expectedTable,
                               actualTable);
    }

    public void testDelete() throws Exception {

        DatabaseOperation.CLEAN_INSERT.execute(getConnection(),
                                               getFlatXmlDataSetFromClasspathResrouce("provider_db_after_insert.xml"));

        getProvider().delete(TinyContentProvider.CONTENT_URI.buildUpon().appendPath("1").build(),
                             null,
                             null);

        // Fetch database data after executing your code
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("tiny");

        // Load expected data from an XML dataset
        IDataSet expectedDataSet = getFlatXmlDataSetFromClasspathResrouce("provider_db_empty.xml");
        ITable expectedTable = expectedDataSet.getTable("tiny");

        // Assert actual database table match expected table
        Assertion.assertEquals(expectedTable,
                               actualTable);
    }

    public void testUpdate() throws Exception {

        DatabaseOperation.CLEAN_INSERT.execute(getConnection(),
                                               getFlatXmlDataSetFromClasspathResrouce("provider_db_after_insert.xml"));

        IDataSet assertDataSet = getConnection().createDataSet();
        ITable assertTable = assertDataSet.getTable("tiny");
        assertEquals(1,
                     assertTable.getRowCount());

        ContentValues values = new ContentValues();
        values.put("name",
                   "buz");
        getProvider().update(TinyContentProvider.CONTENT_URI.buildUpon().appendPath("1").build(),
                             values,
                             null,
                             null);

        // Fetch database data after executing your code
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("tiny");

        // Load expected data from an XML dataset
        IDataSet expectedDataSet = getFlatXmlDataSetFromClasspathResrouce("provider_db_after_update.xml");
        ITable expectedTable = expectedDataSet.getTable("tiny");

        // Assert actual database table match expected table
        Assertion.assertEquals(expectedTable,
                               actualTable);
    }

}
