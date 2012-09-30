package com.example.test;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.uphyca.testing.AndroidJUnit4TestAdapter;

public class AllTests extends TestCase {
    public static junit.framework.Test suite() {

        TestSuite suite = new TestSuite();

        suite.addTest(new AndroidJUnit4TestAdapter(MyTest.class));
        suite.addTest(new AndroidJUnit4TestAdapter(MyTest2.class));

        return suite;
    }

}
