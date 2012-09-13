package com.uphyca.testing;

import android.test.AndroidTestCase;

class AndroidTester extends AndroidTestCase {

    public AndroidTester(Object owner) {
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
