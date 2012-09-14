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

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.test.ServiceTestCase;

class ServiceTester<T extends Service> extends ServiceTestCase<T> {

    public ServiceTester(Object owner,
                               Class<T> serviceClass) {
        super(serviceClass);
        Infrastructure.setContextToAndroidTestCase(this);
        Infrastructure.setPerformanceWriterIfPerformanceCollectorTestCase(owner);
    }

    /* (non-Javadoc)
     * @see android.test.ServiceTestCase#setUp()
     */
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    /* (non-Javadoc)
     * @see android.test.ServiceTestCase#tearDown()
     */
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /* (non-Javadoc)
     * @see android.test.ServiceTestCase#bindService(android.content.Intent)
     */
    @Override
    public IBinder bindService(Intent intent) {
        return super.bindService(intent);
    }

    /* (non-Javadoc)
     * @see android.test.ServiceTestCase#setupService()
     */
    @Override
    public void setupService() {
        super.setupService();
    }

    /* (non-Javadoc)
     * @see android.test.ServiceTestCase#shutdownService()
     */
    @Override
    public void shutdownService() {
        super.shutdownService();
    }

    /* (non-Javadoc)
     * @see android.test.ServiceTestCase#startService(android.content.Intent)
     */
    @Override
    public void startService(Intent intent) {
        super.startService(intent);
    }
}
