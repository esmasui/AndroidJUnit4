package com.uphyca.testing;

import org.junit.After;
import org.junit.Before;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import com.uphyca.testing.app.RobolectricInstrumentation;

public abstract class ActivityInstrumentationTestCase2<T extends Activity> extends ActivityTestCase {

    private final Class<T> mActivityClass;
    private Instrumentation mInstrumentation;
    private Intent mActivityIntent;
    private boolean mInitialTouchMode;

    public ActivityInstrumentationTestCase2(Class<T> mActivityClass) {
        this.mActivityClass = mActivityClass;
        injectInstrumentation(new RobolectricInstrumentation());
    }

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();

        mInitialTouchMode = false;
        mActivityIntent = null;
    }

    @After
    @Override
    public void tearDown() throws Exception {
        // Finish the Activity off (unless was never launched anyway)
        Activity a = super.getActivity();
        if (a != null) {
            a.finish();
            setActivity(null);
        }

        // Scrub out members - protects against memory leaks in the case where
        // someone
        // creates a non-static inner class (thus referencing the test case) and
        // gives it to
        // someone else to hold onto
        scrubClass(ActivityInstrumentationTestCase2.class);

        super.tearDown();
    }

    /**
     * @param instrumentation
     * @see android.test.InstrumentationTestCase#injectInstrumentation(android.app.Instrumentation)
     */
    public void injectInstrumentation(Instrumentation instrumentation) {
        mInstrumentation = instrumentation;
    }

    /**
     * @param instrumentation
     * @deprecated
     * @see android.test.InstrumentationTestCase#injectInsrumentation(android.app.Instrumentation)
     */
    public void injectInsrumentation(Instrumentation instrumentation) {
        injectInstrumentation(instrumentation);
    }

    /**
     * @return
     * @see android.test.InstrumentationTestCase#getInstrumentation()
     */
    public Instrumentation getInstrumentation() {
        return mInstrumentation;
    }

    /**
     * @param i
     * @see android.test.ActivityInstrumentationTestCase2#setActivityIntent(android.content.Intent)
     */
    public void setActivityIntent(Intent i) {
        mActivityIntent = i;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected T getActivity() {
        Activity a = super.getActivity();
        if (a == null) {
            // set initial touch mode
            getInstrumentation().setInTouchMode(mInitialTouchMode);
            final String targetPackage = getInstrumentation().getTargetContext()
                                                             .getPackageName();
            // inject custom intent, if provided
            if (mActivityIntent == null) {
                a = launchActivity(targetPackage, mActivityClass, null);
            } else {
                a = launchActivityWithIntent(targetPackage, mActivityClass, mActivityIntent);
            }
            setActivity(a);
        }
        return (T) a;
    }

    /**
     * @param initialTouchMode
     * @see android.test.ActivityInstrumentationTestCase2#setActivityInitialTouchMode(boolean)
     */
    public void setActivityInitialTouchMode(boolean initialTouchMode) {
        mInitialTouchMode = initialTouchMode;
    }

    /**
     * @param r
     * @throws Throwable
     * @see android.test.InstrumentationTestCase#runTestOnUiThread(java.lang.Runnable)
     */
    public void runTestOnUiThread(Runnable r) throws Throwable {
        r.run();
    }

    /**
     * @param keysSequence
     * @see android.test.InstrumentationTestCase#sendKeys(java.lang.String)
     */
    public void sendKeys(String keysSequence) {
    }

    /**
     * @param keys
     * @see android.test.InstrumentationTestCase#sendKeys(int[])
     */
    public void sendKeys(int... keys) {
    }

    /**
     * @param keys
     * @see android.test.InstrumentationTestCase#sendRepeatedKeys(int[])
     */
    public void sendRepeatedKeys(int... keys) {
    }
}
