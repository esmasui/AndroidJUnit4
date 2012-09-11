package com.uphyca.testing.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import android.test.FlakyTest;

import com.uphyca.testing.InstrumentationTestCase;

public class HelloFlaky extends InstrumentationTestCase {

    private int count;

    @Test
    @FlakyTest(tolerance = 5)
    public void shouldBeTrue() {
        assertThat(++count,
                   equalTo(5));
    }
}
