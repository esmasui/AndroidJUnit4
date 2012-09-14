package com.uphyca.testing.test;

import junit.framework.JUnit4TestAdapter;

import org.junit.Test;

import android.test.suitebuilder.annotation.LargeTest;

public class AnnotatedWithLargeTest {

    public static final junit.framework.Test suite() {
        return new JUnit4TestAdapter(AnnotatedWithLargeTest.class);
    }

    @LargeTest
    @Test
    public void assertPreconditions() {
    }
}
