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

import android.app.Instrumentation;

public interface InstrumentationSupport {

    /**
     * Injects instrumentation into this test case. This method is
     * called by the test runner during test setup.
     * 
     * @param instrumentation the instrumentation to use with this instance
     */
    void injectInstrumentation(Instrumentation instrumentation);


    /**
     * Injects instrumentation into this test case. This method is
     * called by the test runner during test setup.
     *
     * @param instrumentation the instrumentation to use with this instance
     *
     * @deprecated Incorrect spelling,
     * use {@link #injectInstrumentation(android.app.Instrumentation)} instead.
     */
    @Deprecated
    void injectInsrumentation(Instrumentation instrumentation);

    /**
     * Inheritors can access the instrumentation using this.
     * @return instrumentation
     */
    public Instrumentation getInstrumentation();
}
