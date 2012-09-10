package com.uphyca.testing;

import android.app.Activity;
import android.test.SingleLaunchActivityTestCase;

public class SingleLaunchActivityTester<T extends Activity> extends SingleLaunchActivityTestCase<T> {

    public SingleLaunchActivityTester(Object owner,
                                            String pkg,
                                            Class<T> activityClass) {
        super(pkg, activityClass);
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
}
