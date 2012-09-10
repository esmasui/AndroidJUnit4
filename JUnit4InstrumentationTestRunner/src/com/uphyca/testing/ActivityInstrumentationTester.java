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

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    public void scrubClass(Class<?> testCaseClass) throws IllegalAccessException {
        // TODO Auto-generated method stub
        super.scrubClass(testCaseClass);
    }

    @Override
    public void setActivity(Activity testActivity) {
        // TODO Auto-generated method stub
        super.setActivity(testActivity);
    }
}
