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
package com.uphyca.testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;

/**
 * @see android.test.ServiceTestCase
 */
public abstract class ServiceTestCase<T extends Service> {

    private final ServiceTester<T> _tester;

    /**
     * @param serviceClass
     * @see android.test.ServiceTestCase#ServiceTestCase(Class)
     */
    public ServiceTestCase(Class<T> serviceClass) {
        _tester = new ServiceTester<T>(this, serviceClass);
    }

    /**
     * @throws Exception
     * @see android.test.ServiceTestCase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        _tester.setUp();
    }

    /**
     * @throws Exception
     * @see android.test.ServiceTestCase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        _tester.tearDown();
    }

    @Test
    public void testAndroidTestCaseSetupProperly() {
        _tester.testAndroidTestCaseSetupProperly();
    }

    @Test
    public void testServiceTestCaseSetUpProperly() throws Exception {
        _tester.testServiceTestCaseSetUpProperly();
    }

    /**
     * @param context
     * @see android.test.AndroidTestCase#setContext(android.content.Context)
     */
    public void setContext(Context context) {
        _tester.setContext(context);
    }

    /**
     * @return
     * @see android.test.AndroidTestCase#getContext()
     */
    public Context getContext() {
        return _tester.getContext();
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
     * @see android.test.ServiceTestCase#getService()
     */
    public T getService() {
        return _tester.getService();
    }

    /**
     * @param application
     * @see android.test.ServiceTestCase#setApplication(android.app.Application)
     */
    public void setApplication(Application application) {
        _tester.setApplication(application);
    }

    /**
     * @return
     * @see android.test.ServiceTestCase#getApplication()
     */
    public Application getApplication() {
        return _tester.getApplication();
    }

    /**
     * @return
     * @see android.test.ServiceTestCase#getSystemContext()
     */
    public Context getSystemContext() {
        return _tester.getSystemContext();
    }

    /**
     * @param intent
     * @return
     * @see com.uphyca.testing.ServiceTester#bindService(android.content.Intent)
     */
    public IBinder bindService(Intent intent) {
        return _tester.bindService(intent);
    }

    /**
     * 
     * @see com.uphyca.testing.ServiceTester#setupService()
     */
    protected void setupService() {
        _tester.setupService();
    }

    /**
     * 
     * @see com.uphyca.testing.ServiceTester#shutdownService()
     */
    protected void shutdownService() {
        _tester.shutdownService();
    }

    /**
     * @param intent
     * @see com.uphyca.testing.ServiceTester#startService(android.content.Intent)
     */
    protected void startService(Intent intent) {
        _tester.startService(intent);
    }
}
