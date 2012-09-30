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

import junit.framework.JUnit4TestAdapterCache;
import junit.framework.Test;
import junit.framework.TestCase;

import org.junit.runner.Description;

@SuppressWarnings("serial")
public class GeneratingJUnit4TestAdapterCache extends JUnit4TestAdapterCache {

    private Class<TestCase> _generatedClass;

    public void setGeneratedClass(Class<TestCase> generatedClass) {
        _generatedClass = generatedClass;
    }

    @Override
    public Test asTest(final Description description) {
        try {
            if (containsKey(description)) {
                return get(description);
            }

            TestCase testCase = _generatedClass.newInstance();
            testCase.setName(description.getMethodName());

            put(description, testCase);

            return testCase;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
