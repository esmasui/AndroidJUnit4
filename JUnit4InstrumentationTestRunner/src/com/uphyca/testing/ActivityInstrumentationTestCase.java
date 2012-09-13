package com.uphyca.testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;

/**
 * @see android.test.ActivityInstrumentationTestCase
 */
@Deprecated
public abstract class ActivityInstrumentationTestCase<T extends Activity> implements InstrumentationSupport {

    @Rule
    public AndroidAnnotatedMethodRule _androidAnnotatedMethodRule;
    
    private final ActivityInstrumentationTester<T> _tester;

    public ActivityInstrumentationTestCase(String pkg,
                                                 Class<T> activityClass) {
        _tester = new ActivityInstrumentationTester<T>(this, pkg, activityClass);
        _androidAnnotatedMethodRule = new AndroidAnnotatedMethodRule(_tester.getInstrumentation());
    }

    public ActivityInstrumentationTestCase(String pkg,
                                                 Class<T> activityClass,
                                                 boolean initialTouchMode) {
        _tester = new ActivityInstrumentationTester<T>(this, pkg, activityClass, initialTouchMode);
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

    @Test
    public void testActivityTestCaseSetUpProperly() throws Exception {
        _tester.testActivityTestCaseSetUpProperly();
    }

    /**
     * @param instrumentation
     * @see android.test.InstrumentationTestCase#injectInstrumentation(android.app.Instrumentation)
     */
    @Override
    public void injectInstrumentation(Instrumentation instrumentation) {
        InstrumentationTestCaseInjector injector = InstrumentationTestCaseInjector.getInstance();
        injector.injectInstrumentation(_tester, instrumentation);
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
     * @see android.test.InstrumentationTestCase#launchActivity(java.lang.String, java.lang.Class, android.os.Bundle)
     */
    @SuppressWarnings("hiding")
    public final <T extends Activity> T launchActivity(String pkg,
                                                       Class<T> activityCls,
                                                       Bundle extras) {
        return _tester.launchActivity(pkg,
                                      activityCls,
                                      extras);
    }

    /**
     * @return
     * @see android.test.ActivityInstrumentationTestCase#getActivity()
     */
    public T getActivity() {
        return _tester.getActivity();
    }

    /**
     * @param pkg
     * @param activityCls
     * @param intent
     * @return
     * @see android.test.InstrumentationTestCase#launchActivityWithIntent(java.lang.String, java.lang.Class, android.content.Intent)
     */
    @SuppressWarnings("hiding")
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
     * @see com.uphyca.testing.ActivityInstrumentationTester#scrubClass(java.lang.Class)
     */
    protected void scrubClass(Class<?> testCaseClass) throws IllegalAccessException {
        _tester.scrubClass(testCaseClass);
    }

    /**
     * @param testActivity
     * @see com.uphyca.testing.ActivityInstrumentationTester#setActivity(android.app.Activity)
     */
    protected void setActivity(Activity testActivity) {
        _tester.setActivity(testActivity);
    }
}
