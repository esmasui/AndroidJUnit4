package com.uphyca.testing;

import android.net.Uri;
import android.test.SyncBaseInstrumentation;

class SyncBaseInstrumentationTester extends SyncBaseInstrumentation {

    public SyncBaseInstrumentationTester(Object owner) {
        Infrastructure.setInstrumentationToInstrumentationTest(this);
        Infrastructure.setPerformanceWriterIfPerformanceCollectorTestCase(owner);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

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
