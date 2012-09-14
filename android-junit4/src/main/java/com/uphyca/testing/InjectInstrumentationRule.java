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

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

//TODO Use this class instead of Tester's initilization code.
class InjectInstrumentationRule implements TestRule {

    private InstrumentationSupport _test;

    public InjectInstrumentationRule(InstrumentationSupport test) {
        _test = test;
    }

    @Override
    public Statement apply(final Statement statement,
                           final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                _test.injectInstrumentation(Infrastructure.getInstrumentation());
                statement.evaluate();
            }
        };
    }
}
