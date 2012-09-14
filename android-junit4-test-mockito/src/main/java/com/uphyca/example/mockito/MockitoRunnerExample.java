package com.uphyca.example.mockito;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import java.util.List;

import junit.framework.JUnit4TestAdapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import android.os.Looper;
import android.test.UiThreadTest;

import com.uphyca.testing.InstrumentationTestCase;

@RunWith(MockitoJUnitRunner.class)
public class MockitoRunnerExample extends InstrumentationTestCase {

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(MockitoRunnerExample.class);
    }

    @Mock
    private List list;

    @Test
    public void assertPreconditions() {
        assertNotNull(getInstrumentation());
    }

    @Test
    @UiThreadTest
    public void assertNonUiThread() {
        assertSame(Looper.getMainLooper(), Looper.myLooper());
    }

    @Test
    public void assertUiThread() {
        assertNotSame(Looper.getMainLooper(), Looper.myLooper());
    }

    @Test
    public void shouldDoSomething() {
        list.add(100);
    }
}
