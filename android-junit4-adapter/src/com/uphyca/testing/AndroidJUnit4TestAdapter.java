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

import java.io.File;
import java.util.Enumeration;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import android.test.InstrumentationTestCase;

/**
 * Adapter class to run JUnit4 tests on the Android testing environment.
 */
public class AndroidJUnit4TestAdapter extends TestSuite {

    private static final String STUB_METHOD_NAME = "testStub";

    private static final class NullTestCase extends TestCase {

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

    private class JUnit4AdapterTestCase extends InstrumentationTestCase {

        private final JUnit4TestAdapter _adapter;

        public JUnit4AdapterTestCase(JUnit4TestAdapter adapter) {
            _adapter = adapter;
        }

        @Override
        public String getName() {
            return STUB_METHOD_NAME;
        }

        public void testStub() {
        }

        @Override
        public int countTestCases() {
            return 1;
        }

        @Override
        public void run(TestResult result) {

            File outputDir = getInstrumentation().getTargetContext()
                                                 .getCacheDir();
            Class<TestCase> generatedClass;
            try {
                generatedClass = TestCaseClassFactory.createTestCaseClass(_testClass, outputDir);
                _cache.setGeneratedClass(generatedClass);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                _adapter.run(result);
            } catch (Exception e) {
                for (int i = 0; i < _count; ++i) {
                    warning(e.getMessage()).run(result);
                }
            }
        }
    }

    static Test warning(final String message) {
        return new TestCase("warning") {
            @Override
            protected void runTest() {
                fail(message);
            }
        };
    }

    private final Test _nullTest = new NullTestCase();
    private final Class<?> _testClass;
    private final TestCase _theTests;
    private final JUnit4TestAdapter _adapter;
    private final GeneratingJUnit4TestAdapterCache _cache;
    private final int _count;

    public AndroidJUnit4TestAdapter(final Class<?> theClass) {
        super(theClass.getName());
        _testClass = theClass;
        _cache = new GeneratingJUnit4TestAdapterCache();
        _adapter = new JUnit4TestAdapter(theClass,
                                         _cache);

        _count = _adapter.countTestCases();
        _theTests = new JUnit4AdapterTestCase(_adapter);
    }

    @Override
    public int countTestCases() {
        return _count;
    }

    @Override
    public Test testAt(int index) {
        if (index < (_count - 1)) {
            return _nullTest;
        }
        return _theTests;
    }

    @Override
    public int testCount() {
        return _count;
    }

    @Override
    public Enumeration<Test> tests() {
        Enumeration<Test> tests = new Enumeration<Test>() {
            private int _current;

            @Override
            public boolean hasMoreElements() {
                return (_current < _count);
            }

            @Override
            public Test nextElement() {
                return testAt(_current++);
            }
        };
        return tests;
    }
}
