package com.uphyca.testing.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.uphyca.testing.AndroidJUnit4TestAdapter;
import com.uphyca.testing.InstrumentationTestCase;

public class HelloInstrumentation extends InstrumentationTestCase {

    /**
     * For Eclipse with ADT
     */
    public static junit.framework.Test suite() {
        //Should use AndroidJUnit4TestAdapter for to running AndroidDependent TestCases.
        return new AndroidJUnit4TestAdapter(HelloInstrumentation.class);
    }

    @Test
    public void testPreconditions() {
        assertNotNull(getInstrumentation());
        assertNotNull(getInstrumentation().getContext());
        assertNotNull(getInstrumentation().getTargetContext());
    }
}
