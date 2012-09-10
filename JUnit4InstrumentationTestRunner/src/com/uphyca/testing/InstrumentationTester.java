package com.uphyca.testing;

import android.test.InstrumentationTestCase;

class InstrumentationTester extends InstrumentationTestCase {

    public InstrumentationTester(Object owner) {
        Infrastructure.setInstrumentationToInstrumentationTest(this);
        Infrastructure.setPerformanceWriterIfPerformanceCollectorTestCase(owner);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
