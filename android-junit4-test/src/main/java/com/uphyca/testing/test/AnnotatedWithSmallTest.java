package com.uphyca.testing.test;

import junit.framework.JUnit4TestAdapter;

import org.junit.Test;

import android.test.suitebuilder.annotation.SmallTest;

public class AnnotatedWithSmallTest {

    public static final junit.framework.Test suite() {
        return new JUnit4TestAdapter(AnnotatedWithSmallTest.class);
    }

    @SmallTest
    @Test
    public void assertPreconditions() {
    }
}
