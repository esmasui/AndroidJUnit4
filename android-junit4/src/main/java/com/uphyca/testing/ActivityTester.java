package com.uphyca.testing;

import android.app.Activity;
import android.test.ActivityTestCase;

class ActivityTester extends ActivityTestCase {

    public ActivityTester(Object owner) {
        Infrastructure.setInstrumentationToInstrumentationTest(this);
        Infrastructure.setPerformanceWriterIfPerformanceCollectorTestCase(owner);
    }

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
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
     * @see android.test.ActivityTestCase#getActivity()
     */
    @Override
    public Activity getActivity() {
        return super.getActivity();
    }

    /* (non-Javadoc)
     * @see android.test.ActivityTestCase#scrubClass(java.lang.Class)
     */
    @Override
    public void scrubClass(Class<?> testCaseClass) throws IllegalAccessException {
        super.scrubClass(testCaseClass);
    }

    /* (non-Javadoc)
     * @see android.test.ActivityTestCase#setActivity(android.app.Activity)
     */
    @Override
    public void setActivity(Activity testActivity) {
        super.setActivity(testActivity);
    }
}