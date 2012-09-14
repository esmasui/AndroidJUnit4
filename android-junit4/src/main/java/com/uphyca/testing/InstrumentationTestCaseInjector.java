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

import android.annotation.TargetApi;
import android.app.Instrumentation;
import android.os.Build;
import android.test.InstrumentationTestCase;

abstract class InstrumentationTestCaseInjector {

    abstract void injectInstrumentation(InstrumentationTestCase test,
                                        Instrumentation instrumentation);

    private static final InstrumentationTestCaseInjector sInjector = ensureInstance();

    private static final InstrumentationTestCaseInjector ensureInstance() {
        @SuppressWarnings("deprecation")
        final int sdkVersion = Integer.parseInt(Build.VERSION.SDK);
        if (sdkVersion < Build.VERSION_CODES.ECLAIR) {
            return new CupcakeInstrumentationInjector();
        } else {
            return new EclairInstrumentationInjector();
        }
    }

    static InstrumentationTestCaseInjector getInstance() {
        return sInjector;
    }

    private static final class CupcakeInstrumentationInjector extends InstrumentationTestCaseInjector {

        @SuppressWarnings("deprecation")
        @Override
        public void injectInstrumentation(InstrumentationTestCase test,
                                          Instrumentation instrumentation) {
            test.injectInsrumentation(instrumentation);
        }
    }



    private static final class EclairInstrumentationInjector extends InstrumentationTestCaseInjector {

        @TargetApi(5)
        @Override
        public void injectInstrumentation(InstrumentationTestCase test,
                                          Instrumentation instrumentation) {
            test.injectInstrumentation(instrumentation);
        }
    }
}
