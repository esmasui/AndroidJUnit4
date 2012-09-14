package com.uphyca.testing;

import android.annotation.TargetApi;
import android.test.LoaderTestCase;

@TargetApi(11)
class LoaderTester extends LoaderTestCase {

    public LoaderTester(Object owner) {
        Infrastructure.setContextToAndroidTestCase(this);
        Infrastructure.setPerformanceWriterIfPerformanceCollectorTestCase(owner);
    }

    /* (non-Javadoc)
     * @see android.test.AndroidTestCase#setUp()
     */
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    /* (non-Javadoc)
     * @see android.test.AndroidTestCase#tearDown()
     */
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
