package com.uphyca.testing;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.uphyca.testing.robolectric.MainActivity;

@RunWith(AndroidTestRunner.class)
public class ActivityInstrumentationTestCase2Test extends ActivityInstrumentationTestCase2<MainActivity> {

    public ActivityInstrumentationTestCase2Test() {
        super(MainActivity.class);
    }

    @Test
    public void shouldLaunchActivity() {
        MainActivity activity = getActivity();
        assertThat(activity, not(nullValue()));
    }
}
