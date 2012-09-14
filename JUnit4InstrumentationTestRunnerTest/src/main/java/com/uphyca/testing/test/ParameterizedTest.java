package com.uphyca.testing.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import android.test.FlakyTest;

import com.uphyca.testing.InstrumentationTestCase;

@RunWith(Parameterized.class)
public class ParameterizedTest extends InstrumentationTestCase {

    @Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] { { 1 }, { 2 }, { 3 }, { 4 }, { 5 } });
    }

    private int fInput;

    public ParameterizedTest(int input) {
        fInput = input;
    }

    private int count;

    @Test
    @FlakyTest(tolerance = 5)
    public void shouldBeTrue() {
        assertThat(++count, equalTo(fInput));
    }
}
