/*
 * Copyright (C) 2012 uPhyca Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uphyca.testing.junit3.support.v4;

import android.app.Activity;
import android.app.ActivityTrojanHorse;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManagerImplTrojanHorse;
import android.support.v4.app.FragmentStateTrojanHorse;
import android.test.mock.MockApplication;
import android.view.Window;

import com.uphyca.testing.support.v4.MockFragmentActivity;

/**
 * This class provides isolated testing of a single fragment. The fragment under
 * test will be created with minimal connection to the system infrastructure,
 * and you can inject mocked or wrappered versions of many of Fragment's
 * dependencies. Most of the work is handled automatically here by
 * {@link #setUp} and {@link #tearDown}.
 * 
 * <p>
 * If you prefer a functional test, see
 * {@link com.uphyca.testing.junit3.support.v4.FragmentInstrumentationTestCase}.
 * 
 */
public abstract class FragmentUnitTestCase<T extends Fragment> extends FragmentTestCase {

    private Class<T> mFragmentClass;

    private Context mFragmentContext;
    private FragmentActivity mActivity;
    private Application mApplication;
    private MockParent mMockParent;
    private FragmentManagerImplTrojanHorse mFragmentManager;
    private boolean mAttached = false;
    private boolean mCreated = false;
    private boolean mActivityAttached = false;

    public FragmentUnitTestCase(Class<T> fragmentClass) {
        mFragmentClass = fragmentClass;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.uphyca.testing.junit3.support.v4.FragmentTestCase#getFragment()
     */
    @SuppressWarnings("unchecked")
    @Override
    public T getFragment() {
        return (T) super.getFragment();
    }

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // default value for target context, as a default
        mFragmentContext = getInstrumentation().getTargetContext();
    }

    /**
     * Start the fragment under test, providing the arguments it supplied. When
     * you use this method to start the fragment, it will automatically be
     * stopped by {@link #tearDown}.
     * 
     * <p>
     * This method will call onAttach() either onCreate(), but if you wish to
     * further exercise Activity life cycle methods, you must call them yourself
     * from your test case.
     * 
     * <p>
     * <i>Do not call from your setUp() method. You must call this method from
     * each of your test methods.</i>
     * 
     * @param arguments
     *            The Bundle as if supplied to
     *            {@link android.support.v4.Fragment#setArguments(Bundle)}.
     * @param savedInstanceState
     *            The instance state, if you are simulating this part of the
     *            life cycle. Typically null.
     * @param lastNonConfigurationInstance
     *            This Object will be available to the Activity if it calls
     *            {@link android.app.Activity#getLastNonConfigurationInstance()}
     *            . Typically null.
     * @return Returns the Fragment that was created
     */
    protected T startFragment(Bundle arguments,
                              Bundle savedInstanceState,
                              Object lastNonConfigurationInstance) {
        assertFalse("Fragment already created", mCreated);

        if (!mAttached) {
            setFragment(null);
            T newFragment = null;
            try {
                attachActivity(lastNonConfigurationInstance);
                FragmentManager fm = mActivity.getSupportFragmentManager();
                mFragmentManager = FragmentManagerImplTrojanHorse.create(fm);
                FragmentStateTrojanHorse fragmentState = new FragmentStateTrojanHorse();
                fragmentState.setClassName(mFragmentClass.getName());
                fragmentState.setArguments(arguments);
                fragmentState.setSavedFragmentState(savedInstanceState);
                newFragment = fragmentState.instantiate(mActivity);
            } catch (Exception e) {
                assertNotNull(newFragment);
            }

            assertNotNull(newFragment);
            setFragment(newFragment);

            mAttached = true;
        }

        T result = getFragment();
        if (result != null) {

            mFragmentManager.attachActivity(mActivity);
            mFragmentManager.addFragment(result, true);
            getFragmentInstrumentation().callFragmentOnCreate();

            mCreated = true;
        }
        return result;
    }

    private void attachActivity(Object lastNonConfigurationInstance) throws Exception {
        if (mActivityAttached) {
            return;
        }

        IBinder token = null;
        if (mApplication == null) {
            setApplication(new MockApplication());
        }
        if (mActivity == null) {
            setActivity(new MockFragmentActivity());
        }
        ComponentName cn = new ComponentName(mActivity.getClass()
                                                      .getPackage()
                                                      .getName(), mActivity.getClass()
                                                                           .getName());
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setComponent(cn);
        ActivityInfo info = new ActivityInfo();
        CharSequence title = mActivity.getClass()
                                      .getName();
        mMockParent = new MockParent();
        String id = null;
        ActivityTrojanHorse.callAttach(getInstrumentation(), mActivity, mFragmentContext, token, mApplication, intent, info, title, mMockParent, id, lastNonConfigurationInstance);

        mActivityAttached = true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.test.InstrumentationTestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {

        if (mAttached) {
            getFragmentInstrumentation().callFragmentOnDestroy();
        }

        setFragment(null);

        // Scrub out members - protects against memory leaks in the case where
        // someone
        // creates a non-static inner class (thus referencing the test case) and
        // gives it to
        // someone else to hold onto
        // FIXME
        // scrubClass(ActivityInstrumentationTestCase.class);

        super.tearDown();
    }

    /**
     * Set the application for use during the test. You must call this function
     * before calling {@link #startFragment}. If your test does not call this
     * method,
     * 
     * @param application
     *            The Application object that will be injected into the Activity
     *            under test.
     */
    public void setApplication(Application application) {
        mApplication = application;
    }

    /**
     * Set the activity for use during the test. You must call this function
     * before calling {@link #startFragment}. If your test does not call this
     * method,
     * 
     * @param activity
     *            The Activity object that will be injected into the Fragment
     *            under test.
     */
    public void setActivity(FragmentActivity activity) {
        mActivity = activity;
    }

    /**
     * If you wish to inject a Mock, Isolated, or otherwise altered context, you
     * can do so here. You must call this function before calling
     * {@link #startFragment}. If you wish to obtain a real Context, as a
     * building block, use getInstrumentation().getTargetContext().
     */
    public void setFragmentContext(Context fragmentContext) {
        mFragmentContext = fragmentContext;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.uphyca.testing.junit3.support.v4.FragmentTestCase#getFragmentManager
     * ()
     */
    @Override
    protected FragmentManager getFragmentManager() {
        if (mCreated) {
            return getFragment().getFragmentManager();
        }

        if (mActivityAttached) {
            FragmentManager fm = mActivity.getSupportFragmentManager();
            getFragmentInstrumentation().setFragmentManager(fm);
        }

        try {
            attachActivity(null);
            FragmentManager fm = mActivity.getSupportFragmentManager();
            getFragmentInstrumentation().setFragmentManager(fm);
            return fm;
        } catch (Exception e) {
            assertNotNull(null);
            return null;
        }
    }
    
    /**
     * Returns the fragment manager.
     * @return
     */
    protected FragmentActivity getActivity(){
        return mActivity;
    }

    /**
     * This method will return the value if your Fragment under test calls
     * {@link android.app.Activity#setRequestedOrientation}.
     */
    public int getRequestedOrientation() {
        if (mMockParent != null) {
            return mMockParent.mRequestedOrientation;
        }
        return 0;
    }

    /**
     * This method will return the launch intent if your Activity under test
     * calls {@link android.app.Activity#startActivity(Intent)} or
     * {@link android.app.Activity#startActivityForResult(Intent, int)}.
     * 
     * @return The Intent provided in the start call, or null if no start call
     *         was made.
     */
    public Intent getStartedActivityIntent() {
        if (mMockParent != null) {
            return mMockParent.mStartedActivityIntent;
        }
        return null;
    }

    /**
     * This method will return the launch request code if your Activity under
     * test calls
     * {@link android.app.Activity#startActivityForResult(Intent, int)}.
     * 
     * @return The request code provided in the start call, or -1 if no start
     *         call was made.
     */
    public int getStartedActivityRequest() {
        if (mMockParent != null) {
            return mMockParent.mStartedActivityRequest;
        }
        return 0;
    }

    /**
     * This method will notify you if the Activity under test called
     * {@link android.app.Activity#finish()},
     * {@link android.app.Activity#finishFromChild(Activity)}, or
     * {@link android.app.Activity#finishActivity(int)}.
     * 
     * @return Returns true if one of the listed finish methods was called.
     */
    public boolean isFinishCalled() {
        if (mMockParent != null) {
            return mMockParent.mFinished;
        }
        return false;
    }

    /**
     * This method will return the request code if the Activity under test
     * called {@link android.app.Activity#finishActivity(int)}.
     * 
     * @return The request code provided in the start call, or -1 if no finish
     *         call was made.
     */
    public int getFinishedActivityRequest() {
        if (mMockParent != null) {
            return mMockParent.mFinishedActivityRequest;
        }
        return 0;
    }

    /**
     * This mock Activity represents the "parent" activity. By injecting this,
     * we allow the user to call a few more Activity methods, including:
     * <ul>
     * <li>{@link android.app.Activity#getRequestedOrientation()}</li>
     * <li>{@link android.app.Activity#setRequestedOrientation(int)}</li>
     * <li>{@link android.app.Activity#finish()}</li>
     * <li>{@link android.app.Activity#finishActivity(int requestCode)}</li>
     * <li>{@link android.app.Activity#finishFromChild(Activity child)}</li>
     * </ul>
     * 
     * TODO: Make this overrideable, and the unit test can look for calls to
     * other methods
     */
    private static class MockParent extends Activity {

        public int mRequestedOrientation = 0;
        public Intent mStartedActivityIntent = null;
        public int mStartedActivityRequest = -1;
        public boolean mFinished = false;
        public int mFinishedActivityRequest = -1;

        /**
         * Implementing in the parent allows the user to call this function on
         * the tested activity.
         */
        @Override
        public void setRequestedOrientation(int requestedOrientation) {
            mRequestedOrientation = requestedOrientation;
        }

        /**
         * Implementing in the parent allows the user to call this function on
         * the tested activity.
         */
        @Override
        public int getRequestedOrientation() {
            return mRequestedOrientation;
        }

        /**
         * By returning null here, we inhibit the creation of any "container"
         * for the window.
         */
        @Override
        public Window getWindow() {
            return null;
        }

        /**
         * By defining this in the parent, we allow the tested activity to call
         * <ul>
         * <li>{@link android.app.Activity#startActivity(Intent)}</li>
         * <li>{@link android.app.Activity#startActivityForResult(Intent, int)}</li>
         * </ul>
         */
        @Override
        public void startActivityFromChild(Activity child,
                                           Intent intent,
                                           int requestCode) {
            mStartedActivityIntent = intent;
            mStartedActivityRequest = requestCode;
        }

        /**
         * By defining this in the parent, we allow the tested activity to call
         * <ul>
         * <li>{@link android.app.Activity#finish()}</li>
         * <li>{@link android.app.Activity#finishFromChild(Activity child)}</li>
         * </ul>
         */
        @Override
        public void finishFromChild(Activity child) {
            mFinished = true;
        }

        /**
         * By defining this in the parent, we allow the tested activity to call
         * <ul>
         * <li>{@link android.app.Activity#finishActivity(int requestCode)}</li>
         * </ul>
         */
        @Override
        public void finishActivityFromChild(Activity child,
                                            int requestCode) {
            mFinished = true;
            mFinishedActivityRequest = requestCode;
        }
    }
}
