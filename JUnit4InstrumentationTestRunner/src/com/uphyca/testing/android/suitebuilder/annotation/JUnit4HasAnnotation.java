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

import static com.android.internal.util.Predicates.or;

import java.lang.annotation.Annotation;

import org.junit.runner.Description;

import com.android.internal.util.Predicate;

/**
 * Modified version of android.test.suitebuilder.annotation.HasAnnotation.
 * 
 * 
 * A predicate that checks to see if a {@link Description} has a specific annotation, either on the
 * method or on the containing class.
 * 
 * {@hide} Not needed for 1.0 SDK.
 */
public class JUnit4HasAnnotation implements Predicate<Description> {

    private Predicate<Description> hasMethodOrClassAnnotation;

    public JUnit4HasAnnotation(Class<? extends Annotation> annotationClass) {
        this.hasMethodOrClassAnnotation = or(
                new JUnit4HasMethodAnnotation(annotationClass),
                new JUnit4HasClassAnnotation(annotationClass));
    }

    public boolean apply(Description description) {
        return hasMethodOrClassAnnotation.apply(description);
    }
}
