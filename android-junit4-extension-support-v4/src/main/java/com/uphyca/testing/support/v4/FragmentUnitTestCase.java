package com.uphyca.testing.support.v4;

import org.junit.After;
import org.junit.Before;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public abstract class FragmentUnitTestCase<T extends Fragment> {

    private FragmentUnitTester<T> _tester;

    public FragmentUnitTestCase(Class<T> fragmentClass) {
        _tester = new FragmentUnitTester<T>(this, fragmentClass);
    }

    /**
     * @throws Exception
     * @see com.uphyca.testing.support.v4.FragmentUnitTester#setUp()
     */
    @Before
    public void setUp() throws Exception {
        _tester.setUp();
    }

    /**
     * @throws Exception
     * @see com.uphyca.testing.support.v4.FragmentUnitTester#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        _tester.tearDown();
    }

    /**
     * @return
     * @see com.uphyca.testing.junit3.support.v4.FragmentUnitTestCase#getFinishedActivityRequest()
     */
    public int getFinishedActivityRequest() {
        return _tester.getFinishedActivityRequest();
    }

    /**
     * @return
     * @see com.uphyca.testing.junit3.support.v4.FragmentUnitTestCase#getFragment()
     */
    public T getFragment() {
        return _tester.getFragment();
    }

    /**
     * @return
     * @see com.uphyca.testing.junit3.support.v4.FragmentTestCase#getFragmentInstrumentation()
     */
    public FragmentInstrumentation getFragmentInstrumentation() {
        return _tester.getFragmentInstrumentation();
    }

    /**
     * @return
     * @see android.test.InstrumentationTestCase#getInstrumentation()
     */
    public Instrumentation getInstrumentation() {
        return _tester.getInstrumentation();
    }

    /**
     * @return
     * @see com.uphyca.testing.junit3.support.v4.FragmentUnitTestCase#getRequestedOrientation()
     */
    public int getRequestedOrientation() {
        return _tester.getRequestedOrientation();
    }

    /**
     * @return
     * @see com.uphyca.testing.junit3.support.v4.FragmentUnitTestCase#getStartedActivityIntent()
     */
    public Intent getStartedActivityIntent() {
        return _tester.getStartedActivityIntent();
    }

    /**
     * @return
     * @see com.uphyca.testing.junit3.support.v4.FragmentUnitTestCase#getStartedActivityRequest()
     */
    public int getStartedActivityRequest() {
        return _tester.getStartedActivityRequest();
    }

    /**
     * @param instrumentation
     * @see android.test.InstrumentationTestCase#injectInstrumentation(android.app.Instrumentation)
     */
    public void injectInstrumentation(Instrumentation instrumentation) {
        _tester.injectInstrumentation(instrumentation);
    }

    /**
     * @param instrumentation
     * @see com.uphyca.testing.junit3.support.v4.FragmentTestCase#injectSupportInstrumentation(com.uphyca.testing.support.v4.FragmentInstrumentation)
     */
    public void injectSupportInstrumentation(FragmentInstrumentation instrumentation) {
        _tester.injectSupportInstrumentation(instrumentation);
    }

    /**
     * @return
     * @see com.uphyca.testing.junit3.support.v4.FragmentUnitTestCase#isFinishCalled()
     */
    public boolean isFinishCalled() {
        return _tester.isFinishCalled();
    }

    /**
     * @param pkg
     * @param activityCls
     * @param extras
     * @return
     * @see android.test.InstrumentationTestCase#launchActivity(java.lang.String,
     *      java.lang.Class, android.os.Bundle)
     */
    @SuppressWarnings("hiding")
    public final <T extends Activity> T launchActivity(String pkg,
                                                       Class<T> activityCls,
                                                       Bundle extras) {
        return _tester.launchActivity(pkg, activityCls, extras);
    }

    /**
     * @param pkg
     * @param activityCls
     * @param intent
     * @return
     * @see android.test.InstrumentationTestCase#launchActivityWithIntent(java.lang.String,
     *      java.lang.Class, android.content.Intent)
     */
    @SuppressWarnings("hiding")
    public final <T extends Activity> T launchActivityWithIntent(String pkg,
                                                                 Class<T> activityCls,
                                                                 Intent intent) {
        return _tester.launchActivityWithIntent(pkg, activityCls, intent);
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
     * @param activity
     * @see com.uphyca.testing.junit3.support.v4.FragmentUnitTestCase#setActivity(android.support.v4.app.FragmentActivity)
     */
    public void setActivity(FragmentActivity activity) {
        _tester.setActivity(activity);
    }

    /**
     * @param application
     * @see com.uphyca.testing.junit3.support.v4.FragmentUnitTestCase#setApplication(android.app.Application)
     */
    public void setApplication(Application application) {
        _tester.setApplication(application);
    }

    /**
     * @param fragmentContext
     * @see com.uphyca.testing.junit3.support.v4.FragmentUnitTestCase#setFragmentContext(android.content.Context)
     */
    public void setFragmentContext(Context fragmentContext) {
        _tester.setFragmentContext(fragmentContext);
    }

    /**
     * @param arguments
     * @param savedInstanceState
     * @param lastNonConfigurationInstance
     * @return
     * @see com.uphyca.testing.support.v4.FragmentUnitTester#startFragment(android.os.Bundle,
     *      android.os.Bundle, java.lang.Object)
     */
    public T startFragment(Bundle arguments,
                           Bundle savedInstanceState,
                           Object lastNonConfigurationInstance) {
        return _tester.startFragment(arguments, savedInstanceState, lastNonConfigurationInstance);
    }

    /**
     * Returns the fragment manager.
     * 
     * @return
     */
    public FragmentManager getFragmentManager() {
        return _tester.getFragmentManager();
    }

    /**
     * Returns the activity.
     * 
     * @return
     */
    public FragmentActivity getActivity() {
        return _tester.getActivity();
    }
}
