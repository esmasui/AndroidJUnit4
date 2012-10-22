/*
 * Copyright (C) 2012 uPhyca Inc. http://www.uphyca.com/
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
 * limitations under the License
 */
package com.uphyca.testing.junit3.support.v4;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.test.InstrumentationTestCase;

import com.uphyca.testing.support.v4.FragmentInstrumentation;

/**
 * This is common code used to support Fragment test cases. For more useful
 * classes, please see
 * {@link com.uphyca.testing.junit3.support.v4.v4.FragmentUnitTestCase} and
 * {@link com.uphyca.testing.junit3.support.v4.v4.FragmentInstrumentationTestCase}
 * .
 */
public class FragmentTestCase extends InstrumentationTestCase {

    private FragmentInstrumentation mFragmentInstrumentation;
    private FragmentManager mFragmentManager;

    public FragmentTestCase() {
        mFragmentInstrumentation = new FragmentInstrumentation();
    }

    public FragmentInstrumentation getFragmentInstrumentation() {
        return mFragmentInstrumentation;
    }

    public void injectSupportInstrumentation(FragmentInstrumentation instrumentation) {
        mFragmentInstrumentation = instrumentation;
    }

    /**
     * The fragment that will be set up for use in each test method.
     */
    private Fragment mFragment;

    /**
     * @return Returns the fragment under test.
     */
    protected Fragment getFragment() {
        return mFragment;
    }

    /**
     * Set the fragment under test.
     * 
     * @param testFragment
     *            The fragment under test
     */
    protected void setFragment(Fragment testFragment) {
        if (testFragment != null) {
            mFragmentInstrumentation.setFragmentManager(testFragment.getFragmentManager());
        }
        mFragment = testFragment;
    }

    /**
     * @return Returns the fragment manager associated the fragment under test.
     */
    protected FragmentManager getFragmentManager() {
        throw new UnsupportedOperationException();
    }

    /**
     * This function is called by various TestCase implementations, at
     * tearDown() time, in order to scrub out any class variables. This protects
     * against memory leaks in the case where a test case creates a non-static
     * inner class (thus referencing the test case) and gives it to someone else
     * to hold onto.
     * 
     * @param testCaseClass
     *            The class of the derived TestCase implementation.
     * 
     * @throws IllegalAccessException
     */
    protected void scrubClass(final Class<?> testCaseClass) throws IllegalAccessException {
        final Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            final Class<?> fieldClass = field.getDeclaringClass();
            if (testCaseClass.isAssignableFrom(fieldClass) && !field.getType()
                                                                    .isPrimitive() && (field.getModifiers() & Modifier.FINAL) == 0) {
                try {
                    field.setAccessible(true);
                    field.set(this, null);
                } catch (Exception e) {
                    android.util.Log.d("TestCase", "Error: Could not nullify field!");
                }

                if (field.get(this) != null) {
                    android.util.Log.d("TestCase", "Error: Could not nullify field!");
                }
            }
        }
    }
}