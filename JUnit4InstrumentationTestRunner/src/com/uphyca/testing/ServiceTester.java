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

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

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
