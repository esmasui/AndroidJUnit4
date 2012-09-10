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

package com.uphyca.testing.android.suitebuilder.annotation;

import java.lang.annotation.Annotation;

import org.junit.runner.Description;

import com.android.internal.util.Predicate;

/**
 * Modified version of android.test.suitebuilder.annotation.HasAnnotation.
 * 
 * 
 * A predicate that checks to see if a {@link android.test.suitebuilder.TestMethod} has a specific annotation on the
 * containing class. Consider using the public {@link JUnit4HasAnnotation} class instead of this class.
 * 
 * {@hide} Not needed for 1.0 SDK.
 */
class JUnit4HasClassAnnotation implements Predicate<Description> {

    private Class<? extends Annotation> annotationClass;

    public JUnit4HasClassAnnotation(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public boolean apply(Description description) {
        return description.getTestClass().getAnnotation(annotationClass) != null;
    }
}
