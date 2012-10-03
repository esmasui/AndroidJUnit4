package com.uphyca.testing.app;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.IBinder;

import com.uphyca.testing.AndroidTestRunner;
import com.uphyca.testing.robolectric.TestSpyActivity;

@RunWith(AndroidTestRunner.class)
public class RobolectricInstrumentationTest {

    private RobolectricInstrumentation mUnderTest;

    @Before
    public void setUp() throws Exception {
        mUnderTest = new RobolectricInstrumentation();
    }

    @Test
    public void assertPreconditions() {
        assertNotNull(mUnderTest);
    }

    @Test
    public void shouldReturnTheTargetContext() {
        Context targetContext = mUnderTest.getTargetContext();
        assertNotNull(targetContext);
    }

    @Test
    public void shouldCreateTheActivityThatNewActivityCalled() throws InstantiationException,
                                                              IllegalAccessException {
        Class<?> clazz = TestSpyActivity.class;
        Context context = mUnderTest.getTargetContext();
        IBinder token = null;
        Application application = null;
        Intent intent = null;
        ActivityInfo info = null;
        CharSequence title = null;
        Activity parent = null;
        String id = null;
        Object lastNonConfigurationInstance = null;

        Activity newActivity = mUnderTest.newActivity(clazz, context, token, application, intent, info, title, parent, id, lastNonConfigurationInstance);

        assertNotNull(newActivity);
    }
}
