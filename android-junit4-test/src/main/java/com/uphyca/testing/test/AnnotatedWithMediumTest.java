package com.uphyca.testing.test;

import junit.framework.JUnit4TestAdapter;

import org.junit.Test;

import android.test.suitebuilder.annotation.MediumTest;

public class AnnotatedWithMediumTest {

    public static final junit.framework.Test suite() {
        return new JUnit4TestAdapter(AnnotatedWithMediumTest.class);
    }

    @MediumTest
    @Test
    public void assertPreconditions() {
    }
}
