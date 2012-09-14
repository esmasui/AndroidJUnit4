package com.uphyca.testing.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import android.test.FlakyTest;

import com.uphyca.testing.AndroidJUnit4TestAdapter;
import com.uphyca.testing.InstrumentationTestCase;

@RunWith(Enclosed.class)
public class EnclosedTest {

    public static final junit.framework.Test suite() {
        return new AndroidJUnit4TestAdapter(EnclosedTest.class);
    }

    public static final class Nested extends InstrumentationTestCase {
        private int count;

        @Test
        @FlakyTest(tolerance = 5)
        public void shouldBeTrue() {
            assertThat(++count, equalTo(5));
        }
    }

}
