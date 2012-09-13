package com.uphyca.testing;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

class ActivityInstrumentationTester2<T extends Activity> extends ActivityInstrumentationTestCase2<T> {

    @SuppressWarnings("deprecation")
    public ActivityInstrumentationTester2(Object owner,
                                                Class<T> activityClass) {
        super(null, activityClass);
        Infrastructure.setInstrumentationToInstrumentationTest(this);
        Infrastructure.setPerformanceWriterIfPerformanceCollectorTestCase(owner);
    }

    @SuppressWarnings("deprecation")
    public ActivityInstrumentationTester2(Object owner,
                                                String pkg,
                                                Class<T> activityClass) {
        //pkg: ignored - no longer in use.
        super(null, activityClass);
        Infrastructure.setInstrumentationToInstrumentationTest(this);
        Infrastructure.setPerformanceWriterIfPerformanceCollectorTestCase(owner);
    }

    /* (non-Javadoc)
     * @see android.test.ActivityInstrumentationTestCase2#setUp()
     */
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    /* (non-Javadoc)
     * @see android.test.ActivityInstrumentationTestCase2#tearDown()
     */
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
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
