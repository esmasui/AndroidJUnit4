package com.uphyca.testing.test;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class JUnit3WithTestSetUp extends TestCase {

    private static int sGlobalSetupCalled;
    private static int sGlobalTearDownCalled;

    public static Test suite() {
        return new TestSetup(new TestSuite(JUnit3WithTestSetUp.class)) {
            @Override
            protected void setUp() throws Exception {
                ++sGlobalSetupCalled;

                assertEquals(1, sGlobalSetupCalled);
                assertEquals(0, sGlobalTearDownCalled);
            }

            @Override
            protected void tearDown() throws Exception {
                ++sGlobalTearDownCalled;

                assertEquals(1, sGlobalSetupCalled);
                assertEquals(1, sGlobalTearDownCalled);
            }
        };
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        assertEquals(1, sGlobalSetupCalled);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        assertEquals(0, sGlobalTearDownCalled);
    }

    public void testFoo() {
        assertTrue(true);
    }

    public void testBar() {
        assertTrue(true);
    }
}
