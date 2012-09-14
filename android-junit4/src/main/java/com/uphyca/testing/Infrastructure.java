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

import junit.framework.Test;
import android.app.Instrumentation;
import android.content.Context;
import android.os.PerformanceCollector.PerformanceResultsWriter;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;
import android.test.PerformanceCollectorTestCase;

public abstract class Infrastructure {

    private static final ThreadLocal<Context> sContext = new ThreadLocal<Context>();
    private static final ThreadLocal<Context> sTestContext = new ThreadLocal<Context>();
    private static final ThreadLocal<Instrumentation> sInstrumentation = new ThreadLocal<Instrumentation>();
    private static final ThreadLocal<PerformanceResultsWriter> sPerformanceResultsWriter = new ThreadLocal<PerformanceResultsWriter>();

    public static void setContext(Context context) {
        sContext.set(context);
    }

    public static void setTestContext(Context context) {
        sTestContext.set(context);
    }

    public static void setInstrumentation(Instrumentation instrumentation) {
        sInstrumentation.set(instrumentation);
    }

    public static void setPerformanceResultsWriter(PerformanceResultsWriter performanceResultsWriter) {
        sPerformanceResultsWriter.set(performanceResultsWriter);
    }

    public static Context getContext() {
        return sContext.get();
    }

    public static Context getTestContext() {
        return sTestContext.get();
    }

    public static Instrumentation getInstrumentation() {
        return sInstrumentation.get();
    }

    public static PerformanceResultsWriter getPerformanceResultsWriter() {
        return sPerformanceResultsWriter.get();
    }

    public static void setContextToAndroidTestCase(AndroidTestCase test) {
        test.setContext(sContext.get());
        AndroidTestCaseInjector injector = AndroidTestCaseInjector.getInstance();
        injector.setTestContext(test, sTestContext.get());
    }

    public static void setInstrumentationToInstrumentationTest(InstrumentationTestCase test) {
        InstrumentationTestCaseInjector injector = InstrumentationTestCaseInjector.getInstance();
        injector.injectInstrumentation(test, sInstrumentation.get());
    }

    public static void setPerformanceWriterIfPerformanceCollectorTestCase(Object test) {
        if (PerformanceCollectorTestCase.class.isAssignableFrom(test.getClass())) {
            ((PerformanceCollectorTestCase) test).setPerformanceResultsWriter(sPerformanceResultsWriter.get());
        }
    }

    // Taken from android.test.AndroidTestRunner
    public static void setContextIfAndroidTestCase(Test test,
                                                   Context context,
                                                   Context testContext) {
        if (AndroidTestCase.class.isAssignableFrom(test.getClass())) {
            ((AndroidTestCase) test).setContext(context);
            AndroidTestCaseInjector injector = AndroidTestCaseInjector.getInstance();
            injector.setTestContext(((AndroidTestCase) test), sTestContext.get());
        }
    }

    // Taken from android.test.AndroidTestRunner
    public static void setInstrumentationIfInstrumentationTestCase(Test test,
                                                                   Instrumentation instrumentation) {
        if (InstrumentationTestCase.class.isAssignableFrom(test.getClass())) {
            InstrumentationTestCaseInjector injector = InstrumentationTestCaseInjector.getInstance();
            injector.injectInstrumentation(((InstrumentationTestCase) test), sInstrumentation.get());
        }
    }

    // Taken from android.test.AndroidTestRunner
    public static void setPerformanceWriterIfPerformanceCollectorTestCase(Test test,
                                                                          PerformanceResultsWriter writer) {
        if (PerformanceCollectorTestCase.class.isAssignableFrom(test.getClass())) {
            ((PerformanceCollectorTestCase) test).setPerformanceResultsWriter(writer);
        }
    }

    public static void clear() {
        sContext.remove();
        sTestContext.remove();
        sInstrumentation.remove();
        sPerformanceResultsWriter.remove();
    }
}
