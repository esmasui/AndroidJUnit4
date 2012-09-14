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

import android.net.Uri;
import android.test.SyncBaseInstrumentation;

class SyncBaseInstrumentationTester extends SyncBaseInstrumentation {

    public SyncBaseInstrumentationTester(Object owner) {
        Infrastructure.setInstrumentationToInstrumentationTest(this);
        Infrastructure.setPerformanceWriterIfPerformanceCollectorTestCase(owner);
    }

    /* (non-Javadoc)
     * @see android.test.SyncBaseInstrumentation#setUp()
     */
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    /* (non-Javadoc)
     * @see android.test.InstrumentationTestCase#tearDown()
     */
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /* (non-Javadoc)
     * @see android.test.SyncBaseInstrumentation#cancelSyncsandDisableAutoSync()
     */
    @Override
    public void cancelSyncsandDisableAutoSync() {
        super.cancelSyncsandDisableAutoSync();
    }

    /* (non-Javadoc)
     * @see android.test.SyncBaseInstrumentation#syncProvider(android.net.Uri, java.lang.String, java.lang.String)
     */
    @Override
    public void syncProvider(Uri uri,
                             String accountName,
                             String authority) throws Exception {
        super.syncProvider(uri,
                           accountName,
                           authority);
    }
}
