package com.uphyca.testing;

import android.app.Activity;
import android.test.SingleLaunchActivityTestCase;

class SingleLaunchActivityTester<T extends Activity> extends SingleLaunchActivityTestCase<T> {

    public SingleLaunchActivityTester(Object owner,
                                            String pkg,
                                            Class<T> activityClass) {
        super(pkg, activityClass);
        Infrastructure.setInstrumentationToInstrumentationTest(this);
        Infrastructure.setPerformanceWriterIfPerformanceCollectorTestCase(owner);
    }

    /* (non-Javadoc)
     * @see android.test.SingleLaunchActivityTestCase#setUp()
     */
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    /* (non-Javadoc)
     * @see android.test.SingleLaunchActivityTestCase#tearDown()
     */
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
