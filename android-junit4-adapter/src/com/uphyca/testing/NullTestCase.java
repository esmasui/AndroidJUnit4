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

import junit.framework.TestCase;
import junit.framework.TestResult;

public class NullTestCase extends TestCase {

    private static final String STUB_METHOD_NAME = "testStub";

    @Override
    public void run(TestResult result) {
        // noop.
    }

    @Override
    public String getName() {
        return STUB_METHOD_NAME;
    }

    @Override
    public int countTestCases() {
        return 1;
    }

    public void testStub() {
    }
}
