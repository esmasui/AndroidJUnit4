package com.uphyca.testing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityUnitTestCase;

class ActivityUnitTester<T extends Activity> extends ActivityUnitTestCase<T> {

    public ActivityUnitTester(Object owner,
                                    Class<T> activityClass) {
        super(activityClass);
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

    /* (non-Javadoc)
     * @see android.test.ActivityUnitTestCase#startActivity(android.content.Intent, android.os.Bundle, java.lang.Object)
     */
    @Override
    public T startActivity(Intent intent,
                           Bundle savedInstanceState,
                           Object lastNonConfigurationInstance) {
        return super.startActivity(intent,
                                   savedInstanceState,
                                   lastNonConfigurationInstance);
    }
}
