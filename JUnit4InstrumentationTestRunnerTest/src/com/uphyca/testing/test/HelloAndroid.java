package com.uphyca.testing.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.uphyca.testing.AndroidJUnit4TestAdapter;
import com.uphyca.testing.AndroidTestCase;

public class HelloAndroid extends AndroidTestCase {

    /**
     * For Eclipse with ADT
     */
    public static junit.framework.Test suite() {
        //Should use AndroidJUnit4TestAdapter for to running AndroidDependent TestCases.
        return new AndroidJUnit4TestAdapter(HelloAndroid.class);
    }

    @Test
    public void testPreconditions() {
        assertNotNull(getContext());
    }

    @Test
    public void shouldBeValidPackage() {
        assertThat(getContext().getPackageName(), equalTo("com.uphyca.testing.test"));
    }

}
