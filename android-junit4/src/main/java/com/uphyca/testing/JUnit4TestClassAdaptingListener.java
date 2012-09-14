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
import junit.framework.TestResult;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

class JUnit4TestClassAdaptingListener extends RunListener {

    private TestResult _result;
    private JUnit4TestAdapterCache _cache;

    public JUnit4TestClassAdaptingListener(TestResult result,
                                           JUnit4TestAdapterCache cache) {
        this._result = result;
        this._cache = cache;
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        _result.addError(_cache.asTest(failure.getDescription()),
                         failure.getException());
    }

    @Override
    public void testFinished(Description description) throws Exception {
        _result.endTest(_cache.asTest(description));
    }

    @Override
    public void testStarted(Description description) throws Exception {
        _result.startTest(_cache.asTest(description));
    }

}
