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
import java.lang.reflect.Method;

import org.junit.runner.Description;

import android.test.suitebuilder.TestMethod;

import com.android.internal.util.Predicate;

/**
 * Modified version of android.test.suitebuilder.annotation.HasAnnotation.
 *
 * 
 * A predicate that checks to see if a the method represented by {@link TestMethod} has a certain
 * annotation on it. Consider using the public {@link JUnit4HasAnnotation} class instead of this class.
 * 
 * {@hide} Not needed for 1.0 SDK.
 */
class JUnit4HasMethodAnnotation implements Predicate<Description> {

    private final Class<? extends Annotation> annotationClass;

    public JUnit4HasMethodAnnotation(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public boolean apply(Description description) {
        Method method;

        //For Parametarized
        String methodName = description.getMethodName();
        if (methodName == null) {
            return false;
        }

        int parameterIndex = methodName.indexOf('[');
        if (parameterIndex > -1) {
            methodName = methodName.substring(0, parameterIndex);
        }

        try {
            method = description.getTestClass().getMethod(methodName);
        } catch (NoSuchMethodException e) {
            //throw new RuntimeException(description.toString(), e);
            return false;
        }
        //        if (method == null){
        //            throw new RuntimeException("No such method for " + description);
        //        }
        return method.getAnnotation(annotationClass) != null;
        //      return description.getAnnotation(annotationClass) != null;
    }
}
