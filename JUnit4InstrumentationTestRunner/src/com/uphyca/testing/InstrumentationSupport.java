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
