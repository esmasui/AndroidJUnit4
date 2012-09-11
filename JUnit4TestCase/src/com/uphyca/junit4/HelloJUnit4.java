package com.uphyca.junit4;

import junit.framework.JUnit4TestAdapterCache;
import junit.framework.TestResult;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import android.test.InstrumentationTestCase;

@RunWith(JUnit4.class)
public class HelloJUnit4 extends InstrumentationTestCase {

    private static boolean sInvoked;
    private TestResult mResult;

    @Override
    public void run(TestResult result) {
        if (sInvoked) {
            return;
        }
        super.run(result);
    }

    @Override
    public void runBare() throws Throwable {
        JUnitCore junit4 = new JUnitCore();
        JUnit4TestAdapterCache cache = JUnit4TestAdapterCache.getDefault();
        junit4.addListener(new JUnit4TestClassAdaptingListener(mResult, cache));
        junit4.run(getClass());
    }

    @Test
    public void testPreconditions() {
        assertNotNull(getInstrumentation());
    }

    @Test
    public void testHoge() {
        assertTrue(true);
    }

    @Test
    public void testPiyo() {
        assertTrue(true);
    }
}
