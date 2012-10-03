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

import org.junit.After;
import org.junit.Before;

import com.uphyca.testing.app.RobolectricInstrumentation;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;

/* (non-Javadoc)
 * @see android.test.InstrumentationTestCase
 */
public class InstrumentationTestCase {

    private Instrumentation mInstrumentation;

    public InstrumentationTestCase() {
        injectInstrumentation(new RobolectricInstrumentation());
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.test.InstrumentationTestCase#injectInstrumentation(android.app
     * .Instrumentation)
     */
    public void injectInstrumentation(Instrumentation instrumentation) {
        mInstrumentation = instrumentation;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.test.InstrumentationTestCase#injectInsrumentation(android.app
     * .Instrumentation)
     */
    @Deprecated
    public void injectInsrumentation(Instrumentation instrumentation) {
        injectInstrumentation(instrumentation);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.test.InstrumentationTestCase#getInstrumentation()
     */
    public Instrumentation getInstrumentation() {
        return mInstrumentation;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.test.InstrumentationTestCase#launchActivity(java.lang.String,
     * java.lang.Class, android.os.Bundle)
     */
    public final <T extends Activity> T launchActivity(String pkg,
                                                       Class<T> activityCls,
                                                       Bundle extras) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        if (extras != null) {
            intent.putExtras(extras);
        }
        return launchActivityWithIntent(pkg, activityCls, intent);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.test.InstrumentationTestCase#launchActivityWithIntent(java.lang
     * .String, java.lang.Class, android.content.Intent)
     */
    @SuppressWarnings("unchecked")
    public final <T extends Activity> T launchActivityWithIntent(String pkg,
                                                                 Class<T> activityCls,
                                                                 Intent intent) {
        intent.setClassName(pkg, activityCls.getName());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        T activity = (T) getInstrumentation().startActivitySync(intent);
        getInstrumentation().waitForIdleSync();
        return activity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.test.InstrumentationTestCase#runTestOnUiThread(java.lang.Runnable
     * )
     */
    public void runTestOnUiThread(final Runnable r) throws Throwable {
        final Throwable[] exceptions = new Throwable[1];
        getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                try {
                    r.run();
                } catch (Throwable throwable) {
                    exceptions[0] = throwable;
                }
            }
        });
        if (exceptions[0] != null) {
            throw exceptions[0];
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.test.InstrumentationTestCase#sendKeys(java.lang.String)
     */
    public void sendKeys(String keysSequence) {
        // TODO Implements sendKeys(String)
        throw new UnsupportedOperationException("Not implemented");
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.test.InstrumentationTestCase#sendKeys(int[])
     */
    public void sendKeys(int... keys) {
        // TODO Implement sendKeys(int...)
        throw new UnsupportedOperationException("Not implemented");
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.test.InstrumentationTestCase#sendRepeatedKeys(int[])
     */
    public void sendRepeatedKeys(int... keys) {
        // TODO Implement sendRepeatedKeys
        throw new UnsupportedOperationException("Not implemented");
    }
}
