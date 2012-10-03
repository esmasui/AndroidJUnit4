package com.uphyca.testing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.content.Intent;
import android.widget.TextView;

import com.uphyca.testing.ActivityUnitTestCase;
import com.uphyca.testing.AndroidTestRunner;
import com.uphyca.testing.robolectric.MainActivity;
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
        assertNotNull(getActivity());
    }

    @Test
    public void shouldHaveTheMessage() {
        MainActivity underTest = startActivity(_startIntent, null, null);
        TextView text = (TextView) underTest.findViewById(R.id.message);
        assertThat(text.getText()
                       .toString(), is("Hello world!"));
    }
}
