package com.uphyca.testing;

import android.app.Application;
import android.test.ApplicationTestCase;

class ApplicationTester<T extends Application> extends ApplicationTestCase<T> {

    public ApplicationTester(Object owner,
                                   Class<T> applicationClass) {
        super(applicationClass);
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
     * @see android.test.AndroidTestCase#scrubClass(java.lang.Class)
     */
    @Override
    public void scrubClass(Class<?> testCaseClass) throws IllegalAccessException {
        super.scrubClass(testCaseClass);
    }
}
