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
package com.uphyca.testing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;

public abstract class ActivityUnitTestCase<T extends Activity> {

    private Instrumentation mInstrumentation;
    private Class<T> mActivityClass;
    private T mActivity;
    private boolean mAttached = false;
    private boolean mCreated = false;

    public ActivityUnitTestCase(Class<T> activityClass) {
        mActivityClass = activityClass;
        injectInstrumentation(new RobolectricInstrumentation());
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
        setActivity(null);
    }

    @SuppressWarnings("unchecked")
    protected void setActivity(Activity testActivity) {
        mActivity = (T) testActivity;
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

                newActivity = mActivityClass.newInstance();

            } catch (Exception e) {
                assertNotNull(newActivity);
            }

            assertNotNull(newActivity);
            setActivity(newActivity);

            mAttached = true;
        }

        T result = getActivity();
        if (result != null) {
            mInstrumentation.callActivityOnCreate(mActivity, savedInstanceState);
            mCreated = true;
        }
        return result;
    }

    protected T getActivity() {
        return mActivity;
    }

    public Instrumentation getInstrumentation() {
        return mInstrumentation;
    }

    public void injectInstrumentation(Instrumentation instrumentation) {
        mInstrumentation = instrumentation;
    }
}
