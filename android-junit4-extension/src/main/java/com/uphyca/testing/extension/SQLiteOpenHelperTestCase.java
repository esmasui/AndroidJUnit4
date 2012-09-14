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
package com.uphyca.testing.extension;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.uphyca.testing.AndroidTestCaseMethodInvoker;

/**
 * @param <T>
 * @see com.uphyca.testing.junit3.SQLiteOpenHelperTestCase
 */
public abstract class SQLiteOpenHelperTestCase<T extends SQLiteOpenHelper> {

    private final SQLiteOpenHelperTester<T> _tester;

    public SQLiteOpenHelperTestCase(Class<T> openHelperClass) {
        _tester = new SQLiteOpenHelperTester<T>(this, openHelperClass);
    }

    /**
     * @throws Exception
     * @see com.uphyca.testing.junit3.SQLiteOpenHelperTestCase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        _tester.setUp();
    }

    /**
     * @throws Exception
     * @see com.uphyca.testing.junit3.SQLiteOpenHelperTestCase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        _tester.tearDown();
    }

    /**
     * @throws Exception
     * @see com.uphyca.testing.junit3.SQLiteOpenHelperTestCase#testAndroidTestCaseSetupProperly()
     */
    @Test
    public void testAndroidTestCaseSetupProperly() {
        _tester.testAndroidTestCaseSetupProperly();
    }

    /**
     * @param testCaseClass
     * @throws IllegalAccessException
     * @see com.uphyca.testing.junit3.SQLiteOpenHelperTestCase#scrubClass(java.lang.Class)
     */
    public void scrubClass(Class<?> testCaseClass) throws IllegalAccessException {
        _tester.scrubClass(testCaseClass);
    }

    /**
     * @param context
     * @return
     * @throws Exception
     * @see com.uphyca.testing.junit3.SQLiteOpenHelperTestCase#createSQLiteOpenHelper(android.content.Context)
     */
    protected T createSQLiteOpenHelper(Context context) throws Exception {
        return _tester.createSQLiteOpenHelper(context);
    }

    /**
     * @param context
     * @see com.uphyca.testing.junit3.SQLiteOpenHelperTestCase#setContext(android.content.Context)
     */
    public void setContext(Context context) {
        _tester.setContext(context);
    }

    /**
     * @return
     * @see com.uphyca.testing.junit3.SQLiteOpenHelperTestCase#getContext()
     */
    public Context getContext() {
        return _tester.getContext();
    }

    /**
     * @see com.uphyca.testing.junit3.SQLiteOpenHelperTestCase#getMockContext()
     * @return
     */
    public Context getMockContext() {
        return _tester.getMockContext();
    }

    /**
     * @param packageName
     * @param className
     * @param permission
     * @see android.test.AndroidTestCase#assertActivityRequiresPermission(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public void assertActivityRequiresPermission(String packageName,
                                                 String className,
                                                 String permission) {
        AndroidTestCaseMethodInvoker invoker = AndroidTestCaseMethodInvoker.getInstance();
        invoker.assertActivityRequiresPermission(_tester, packageName, className, permission);
    }

    /**
     * @param uri
     * @param permission
     * @see android.test.AndroidTestCase#assertReadingContentUriRequiresPermission(android.net.Uri,
     *      java.lang.String)
     */
    public void assertReadingContentUriRequiresPermission(Uri uri,
                                                          String permission) {
        AndroidTestCaseMethodInvoker invoker = AndroidTestCaseMethodInvoker.getInstance();
        invoker.assertReadingContentUriRequiresPermission(_tester, uri, permission);
    }

    /**
     * @param uri
     * @param permission
     * @see android.test.AndroidTestCase#assertWritingContentUriRequiresPermission(android.net.Uri,
     *      java.lang.String)
     */
    public void assertWritingContentUriRequiresPermission(Uri uri,
                                                          String permission) {
        AndroidTestCaseMethodInvoker invoker = AndroidTestCaseMethodInvoker.getInstance();
        invoker.assertWritingContentUriRequiresPermission(_tester, uri, permission);
    }

    /**
     * @return
     * @see com.uphyca.testing.junit3.SQLiteOpenHelperTestCase#getSQLiteOpenHelper()
     */
    public T getSQLiteOpenHelper() {
        return _tester.getSQLiteOpenHelper();
    }
}
