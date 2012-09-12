package com.uphyca.testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;

public abstract class ActivityInstrumentationTestCase2<T extends Activity> implements InstrumentationSupport {

    @Rule
    public AndroidAnnotatedMethodRule _androidAnnotatedMethodRule;

    private final ActivityInstrumentationTester2<T> _tester;

    public ActivityInstrumentationTestCase2(Class<T> activityClass) {
        _tester = new ActivityInstrumentationTester2<T>(this, activityClass);
        _androidAnnotatedMethodRule = new AndroidAnnotatedMethodRule(_tester.getInstrumentation());
    }

    @Deprecated
    public ActivityInstrumentationTestCase2(String pkg,
                                            Class<T> activityClass) {
        _tester = new ActivityInstrumentationTester2<T>(this, pkg, activityClass);
        _androidAnnotatedMethodRule = new AndroidAnnotatedMethodRule(_tester.getInstrumentation());
    }

    @Before
    public void setUp() throws Exception {
        _tester.setUp();
    }

    @After
    public void tearDown() throws Exception {
        _tester.tearDown();
    }

    /**
     * @param instrumentation
     * @see android.test.InstrumentationTestCase#injectInstrumentation(android.app.Instrumentation)
     */
    @Override
    public void injectInstrumentation(Instrumentation instrumentation) {
        InstrumentationTestCaseInjector injector = InstrumentationTestCaseInjector.getInstance();
        injector.injectInstrumentation(_tester,
                                       instrumentation);
    }

    /**
     * @param instrumentation
     * @deprecated
     * @see android.test.InstrumentationTestCase#injectInsrumentation(android.app.Instrumentation)
     */
    @Override
    public void injectInsrumentation(Instrumentation instrumentation) {
        injectInstrumentation(instrumentation);
    }

    /**
     * @return
     * @see android.test.InstrumentationTestCase#getInstrumentation()
     */
    @Override
    public Instrumentation getInstrumentation() {
        return _tester.getInstrumentation();
    }

    /**
     * @param pkg
     * @param activityCls
     * @param extras
     * @return
     * @see android.test.InstrumentationTestCase#launchActivity(java.lang.String,
     *      java.lang.Class, android.os.Bundle)
     */
    public final <T extends Activity> T launchActivity(String pkg,
                                                       Class<T> activityCls,
                                                       Bundle extras) {
        return _tester.launchActivity(pkg,
                                      activityCls,
                                      extras);
    }

    /**
     * @return
     * @see android.test.ActivityInstrumentationTestCase2#getActivity()
     */
    public T getActivity() {
        return _tester.getActivity();
    }

    /**
     * @param pkg
     * @param activityCls
     * @param intent
     * @return
     * @see android.test.InstrumentationTestCase#launchActivityWithIntent(java.lang.String,
     *      java.lang.Class, android.content.Intent)
     */
    public final <T extends Activity> T launchActivityWithIntent(String pkg,
                                                                 Class<T> activityCls,
                                                                 Intent intent) {
        return _tester.launchActivityWithIntent(pkg,
                                                activityCls,
                                                intent);
    }

    /**
     * @param r
     * @throws Throwable
     * @see android.test.InstrumentationTestCase#runTestOnUiThread(java.lang.Runnable)
     */
    public void runTestOnUiThread(Runnable r) throws Throwable {
        _tester.runTestOnUiThread(r);
    }

    /**
     * @param i
     * @see android.test.ActivityInstrumentationTestCase2#setActivityIntent(android.content.Intent)
     */
    public void setActivityIntent(Intent i) {
        _tester.setActivityIntent(i);
    }

    /**
     * @param initialTouchMode
     * @see android.test.ActivityInstrumentationTestCase2#setActivityInitialTouchMode(boolean)
     */
    public void setActivityInitialTouchMode(boolean initialTouchMode) {
        _tester.setActivityInitialTouchMode(initialTouchMode);
    }

    /**
     * @param keysSequence
     * @see android.test.InstrumentationTestCase#sendKeys(java.lang.String)
     */
    public void sendKeys(String keysSequence) {
        _tester.sendKeys(keysSequence);
    }

    /**
     * @param keys
     * @see android.test.InstrumentationTestCase#sendKeys(int[])
     */
    public void sendKeys(int... keys) {
        _tester.sendKeys(keys);
    }

    /**
     * @param keys
     * @see android.test.InstrumentationTestCase#sendRepeatedKeys(int[])
     */
    public void sendRepeatedKeys(int... keys) {
        _tester.sendRepeatedKeys(keys);
    }

    /**
     * @param testCaseClass
     * @throws IllegalAccessException
     * @see com.uphyca.testing.ActivityInstrumentationTester2#scrubClass(java.lang.Class)
     */
    protected void scrubClass(Class<?> testCaseClass) throws IllegalAccessException {
        _tester.scrubClass(testCaseClass);
    }

    /**
     * @param testActivity
     * @see com.uphyca.testing.ActivityInstrumentationTester2#setActivity(android.app.Activity)
     */
    protected void setActivity(Activity testActivity) {
        _tester.setActivity(testActivity);
    }
}
