/*
 * Copyright (C) 2012 uPhyca Inc.
 * 
 * Base on previous work by
 * Copyright (C) 2008 The Android Open Source Project
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
package com.uphyca.testing;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Window;

import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.shadows.ShadowApplication;

public abstract class ActivityUnitTestCase<T extends Activity> extends ActivityTestCase {

    private Class<T> mActivityClass;

    private Context mActivityContext;
    private Application mApplication;
    private MockParent mMockParent;

    private boolean mAttached = false;
    private boolean mCreated = false;

    public ActivityUnitTestCase(Class<T> activityClass) {
        mActivityClass = activityClass;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getActivity() {
        return (T) super.getActivity();
    }

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        mActivityContext = getInstrumentation().getTargetContext();
    }

    protected T startActivity(Intent intent,
                              Bundle savedInstanceState,
                              Object lastNonConfigurationInstance) {
        assertFalse("Activity already created", mCreated);

        if (!mAttached) {
            assertNotNull(mActivityClass);
            setActivity(null);
            T newActivity = null;
            try {
                IBinder token = null;
                if (mApplication == null) {
                    setApplication(Robolectric.application);
                }
                ComponentName cn = new ComponentName(mActivityClass.getPackage()
                                                                   .getName(), mActivityClass.getName());
                intent.setComponent(cn);
                ActivityInfo info = new ActivityInfo();
                CharSequence title = mActivityClass.getName();
                mMockParent = new MockParent();
                String id = null;

                newActivity = (T) getInstrumentation().newActivity(mActivityClass, mActivityContext, token, mApplication, intent, info, title, mMockParent, id, lastNonConfigurationInstance);
            } catch (Exception e) {
                assertNotNull(newActivity);
            }

            assertNotNull(newActivity);
            setActivity(newActivity);

            mAttached = true;
        }

        T result = getActivity();
        if (result != null) {
            getInstrumentation().callActivityOnCreate(getActivity(), savedInstanceState);
            mCreated = true;
        }
        return result;
    }

    @Override
    @After
    public void tearDown() throws Exception {
        setActivity(null);
        super.tearDown();
    }

    public void setApplication(Application application) {
        if (application == Robolectric.application) {
            return;
        }

        bindApplication(application);
        mApplication = application;
        Robolectric.application = application;
    }

    public Application bindApplication(Application application) {

        ShadowApplication shadowApplication = shadowOf(application);
        ShadowApplication.bind(application, Robolectric.getShadowApplication()
                                                       .getResourceLoader());
        shadowApplication.setPackageName(Robolectric.getShadowApplication()
                                                    .getPackageName());
        shadowApplication.setPackageManager(Robolectric.getShadowApplication()
                                                       .getPackageManager());

        return application;
    }

    public void setActivityContext(Context activityContext) {
        mActivityContext = activityContext;
    }

    public int getRequestedOrientation() {
        return Robolectric.shadowOf(getActivity())
                          .getRequestedOrientation();
        // if (mMockParent != null) {
        // return mMockParent.mRequestedOrientation;
        // }
        // return 0;
    }

    public Intent getStartedActivityIntent() {
        return Robolectric.shadowOf(getActivity())
                          .getNextStartedActivity();
        // if (mMockParent != null) {
        // return mMockParent.mStartedActivityIntent;
        // }
        // return null;
    }

    public int getStartedActivityRequest() {
        return Robolectric.shadowOf(getActivity())
                          .getNextStartedActivityForResult().requestCode;
        // if (mMockParent != null) {
        // return mMockParent.mStartedActivityRequest;
        // }
        // return 0;
    }

    public boolean isFinishCalled() {
        return Robolectric.shadowOf(getActivity())
                          .isFinishing();
        // if (mMockParent != null) {
        // return mMockParent.mFinished;
        // }
        // return false;
    }

    public int getFinishedActivityRequest() {
        return Robolectric.shadowOf(getActivity())
                          .getResultCode();
    }

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
