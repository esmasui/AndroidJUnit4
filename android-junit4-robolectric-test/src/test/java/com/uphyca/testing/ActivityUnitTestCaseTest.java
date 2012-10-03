/*
 * Copyright (C) 2012 uPhyca Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uphyca.testing;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;

import com.uphyca.testing.robolectric.TestSpyActivity;

@RunWith(AndroidTestRunner.class)
public class ActivityUnitTestCaseTest extends ActivityUnitTestCase<TestSpyActivity> {

    private Intent _startIntent;

    public ActivityUnitTestCaseTest() {
        super(TestSpyActivity.class);
    }

    @Override
    @Before
    public void setUp() throws Exception {

        super.setUp();

        _startIntent = new Intent(Intent.ACTION_MAIN);
    }

    @Override
    @After
    public void tearDown() throws Exception {

        TestSpyActivity underTest = getActivity();

        assertFalse(underTest.isOnDestroyCalled());

        super.tearDown();

        assertFalse(underTest.isOnDestroyCalled());
    }

    @Test
    public void assertPreconditions() {
        startActivity(_startIntent, null, null);
        assertNotNull(getActivity());
    }

    @Test
    public void exerciseLifecycle() {

        TestSpyActivity underTest;

        // Activate activity

        startActivity(_startIntent, null, null);

        underTest = getActivity();

        assertTrue(underTest.isOnCreateCalled());

        assertFalse(underTest.isOnStartCalled());
        getInstrumentation().callActivityOnStart(underTest);
        assertTrue(underTest.isOnStartCalled());

        assertFalse(underTest.isOnResumeCalled());
        getInstrumentation().callActivityOnResume(underTest);
        assertTrue(underTest.isOnResumeCalled());

        // Deactivate activity

        assertFalse(underTest.isOnPauseCalled());
        getInstrumentation().callActivityOnPause(underTest);
        assertTrue(underTest.isOnPauseCalled());

        assertFalse(underTest.isOnStopCalled());
        getInstrumentation().callActivityOnStop(underTest);
        assertTrue(underTest.isOnStopCalled());
    }

    /**
     * Test test for getStartedActivityIntent();
     */
    @Test
    public void shouldReturnsIntentThatStartActivityCalled() {

        startActivity(_startIntent, null, null);

        assertNull(getStartedActivityIntent());

        Intent newIntent = new Intent(Intent.ACTION_VIEW);
        getActivity().startActivity(newIntent);

        assertThat(getStartedActivityIntent(), is(newIntent));
    }

    /**
     * The test for getStartedActivityRequest()
     */
    @Test
    public void shouldReturnsIntentThatStartActivityForResultCalled() {
        final int requestCode = 1;

        startActivity(_startIntent, null, null);

        assertNull(getStartedActivityIntent());

        Intent newIntent = new Intent(Intent.ACTION_VIEW);

        getActivity().startActivityForResult(newIntent, requestCode);

        assertThat(getStartedActivityIntent(), is(newIntent));
        assertThat(getStartedActivityRequest(), is(requestCode));
    }

    /**
     * The test for getRequestOrientation()
     */
    @Test
    public void shouldReturnsOrientationThatSetRequestOrientationCalled() {

        startActivity(_startIntent, null, null);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        assertThat(getRequestedOrientation(), is(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE));
    }

    /**
     * The test for isFinishCalled()
     */
    @Test
    public void shouldReturnsTrueThatFinishCalled() {

        startActivity(_startIntent, null, null);

        assertFalse(isFinishCalled());

        getActivity().finish();

        assertTrue(isFinishCalled());
    }

    /**
     * The test for getFinishedActivityRequest();
     */
    public void shouldReturnsResultCodeWhenResultCodeSet() {

        startActivity(_startIntent, null, null);

        assertThat(getFinishedActivityRequest(), is(Activity.RESULT_CANCELED));

        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();

        assertThat(getFinishedActivityRequest(), is(Activity.RESULT_OK));
    }
}
