/*
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

package com.uphyca.testing.android.suitebuilder;

import org.junit.Ignore;
import org.junit.runner.Description;

import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.test.suitebuilder.annotation.Smoke;
import android.test.suitebuilder.annotation.Suppress;

import com.android.internal.util.Predicate;
import com.android.internal.util.Predicates;
import com.uphyca.testing.InstrumentationSupport;
import com.uphyca.testing.android.suitebuilder.annotation.JUnit4HasAnnotation;

/**
 * Modified version of android.test.suitebuilder.TestPredicates.
 * 
 * 
 * {@hide} Not needed for 1.0 SDK.
 */
public class JUnit4TestPredicates {

    public static final Predicate<Description> SELECT_INSTRUMENTATION_JUNIT3 =
            new JUnit4AssignableFrom(InstrumentationTestCase.class);
    public static final Predicate<Description> SELECT_INSTRUMENTATION_JUNIT4 =
            new JUnit4AssignableFrom(InstrumentationSupport.class);
    @SuppressWarnings("unchecked")
    public static final Predicate<Description> SELECT_INSTRUMENTATION =
            Predicates.or(SELECT_INSTRUMENTATION_JUNIT3, SELECT_INSTRUMENTATION_JUNIT4);
    public static final Predicate<Description> REJECT_INSTRUMENTATION =
            Predicates.not(SELECT_INSTRUMENTATION);

    public static final Predicate<Description> SELECT_SMOKE = new JUnit4HasAnnotation(Smoke.class);
    public static final Predicate<Description> SELECT_SMALL = new JUnit4HasAnnotation(SmallTest.class);
    public static final Predicate<Description> SELECT_MEDIUM = new JUnit4HasAnnotation(MediumTest.class);
    public static final Predicate<Description> SELECT_LARGE = new JUnit4HasAnnotation(LargeTest.class);
    public static final Predicate<Description> REJECT_SUPPRESSED =
            Predicates.not(Predicates.or(new JUnit4HasAnnotation(Suppress.class), new JUnit4HasAnnotation(Ignore.class)));

}
