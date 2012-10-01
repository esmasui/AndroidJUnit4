package com.uphyca.testing.robolectric;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Activity;
import android.content.Intent;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.TextView;

import com.uphyca.testing.ActivityUnitTestCase;
import com.uphyca.testing.AndroidTestRunner;
import com.uphyca.testing.robolectric.test.R;

@RunWith(AndroidTestRunner.class)
public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {

    private Intent _startIntent;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Before
    public void before() {
        _startIntent = new Intent(Intent.ACTION_MAIN);
    }

    @Test
    public void assertPreconditions() {
        startActivity(_startIntent, null, null);
    }

    @Test
    public void shouldHaveTheMessage() {
        MainActivity underTest = startActivity(_startIntent, null, null);
        TextView text = (TextView) underTest.findViewById(R.id.message);
        assertThat(text.getText()
                       .toString(), is("Hello world!"));
    }

    /**
     * This test demonstrates ways to exercise the Activity's life cycle.
     */
    @MediumTest
    @Test
    public void exerciseLifecycle() {
        Activity underTest = startActivity(_startIntent, null, null);

        // At this point, onCreate() has been called, but nothing else
        // Complete the startup of the activity
        getInstrumentation().callActivityOnStart(underTest);
        getInstrumentation().callActivityOnResume(underTest);

        // At this point you could test for various configuration aspects, or
        // you could
        // use a Mock Context to confirm that your activity has made certain
        // calls to the system
        // and set itself up properly.

        getInstrumentation().callActivityOnPause(underTest);

        // At this point you could confirm that the activity has paused
        // properly, as if it is
        // no longer the topmost activity on screen.

        getInstrumentation().callActivityOnStop(underTest);

        // At this point, you could confirm that the activity has shut itself
        // down appropriately,
        // or you could use a Mock Context to confirm that your activity has
        // released any system
        // resources it should no longer be holding.

        // ActivityUnitTestCase.tearDown(), which is always automatically
        // called, will take care
        // of calling onDestroy().
    }

}
