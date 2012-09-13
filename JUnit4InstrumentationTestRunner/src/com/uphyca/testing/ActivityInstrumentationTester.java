package com.uphyca.testing;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase;

class ActivityInstrumentationTester<T extends Activity> extends ActivityInstrumentationTestCase<T> {

    public ActivityInstrumentationTester(Object owner,
                                         String pkg,
                                         Class<T> activityClass) {
        super(pkg, activityClass);
        Infrastructure.setInstrumentationToInstrumentationTest(this);
        Infrastructure.setPerformanceWriterIfPerformanceCollectorTestCase(owner);
    }

    public ActivityInstrumentationTester(Object owner,
                                         String pkg,
                                         Class<T> activityClass,
                                         boolean initialTouchMode) {
        super(pkg, activityClass, initialTouchMode);
        Infrastructure.setInstrumentationToInstrumentationTest(this);
        Infrastructure.setPerformanceWriterIfPerformanceCollectorTestCase(owner);
    }

    /* (non-Javadoc)
     * @see android.test.ActivityInstrumentationTestCase#setUp()
     */
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    /* (non-Javadoc)
     * @see android.test.ActivityInstrumentationTestCase#tearDown()
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
