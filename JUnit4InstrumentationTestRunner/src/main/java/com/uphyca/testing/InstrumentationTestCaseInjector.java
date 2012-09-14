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
