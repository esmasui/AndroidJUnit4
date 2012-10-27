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
package com.uphyca.testing.junit3.support.v4.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uphyca.testing.junit3.support.v4.FragmentUnitTestCase;
import com.uphyca.testing.junit3.support.v4.test.FragmentUnitTestCaseTest.MyFragment;

public class FragmentUnitTestCaseTest extends FragmentUnitTestCase<MyFragment> {

    public static final class MyFragment extends Fragment {
        public boolean onCreateCalled;
        public boolean onAttachCalled;
        public boolean onActivityCreatedCalled;
        public boolean onStartCalled;
        public boolean onResumeCalled;
        public boolean onPauseCalled;
        public boolean onStopCalled;
        public boolean onDestroyCalled;
        public boolean onDettachCalled;
        public boolean onCreateViewCalled;
        public boolean onDestroyViewCalled;
        private boolean onViewCreatedCalled;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            onCreateCalled = true;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            onAttachCalled = true;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            onActivityCreatedCalled = true;
        }

        @Override
        public void onStart() {
            super.onStart();
            onStartCalled = true;
        }

        @Override
        public void onResume() {
            super.onResume();
            onResumeCalled = true;
        }

        @Override
        public void onPause() {
            super.onPause();
            onPauseCalled = true;
        }

        @Override
        public void onStop() {
            super.onStop();
            onStopCalled = true;
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            onDestroyCalled = true;
        }

        @Override
        public void onDetach() {
            super.onDetach();
            onDettachCalled = true;
        }

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container,
                                 Bundle savedInstanceState) {
            try {
                return null;
            } finally {
                onCreateViewCalled = true;
            }
        }
        
        @Override
        public void onViewCreated(View view,
                                  Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            onViewCreatedCalled = true;
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            onDestroyViewCalled = true;
        }
    }

    public FragmentUnitTestCaseTest() {
        super(MyFragment.class);
    }

    @Override
    protected void tearDown() throws Exception {

        MyFragment fragment = getFragment();

        if (fragment != null) {
            assertFalse(fragment.onDestroyViewCalled);
            assertFalse(fragment.onDestroyCalled);
            assertFalse(fragment.onDettachCalled);
        }

        super.tearDown();

        if (fragment != null) {
            assertTrue(fragment.onDestroyCalled);
            assertTrue(fragment.onDettachCalled);
        }
    }

    public void testPreconditions() {
        startFragment(null, null, null);
        assertNotNull(getFragment());
    }

    public void testOnCreate() {
        startFragment(null, null, null);
        assertTrue(getFragment().onAttachCalled);
        assertTrue(getFragment().onCreateCalled);
    }

    public void testLifeCycles() {

        // Activate fragment

        startFragment(null, null, null);

        assertTrue(getFragment().onAttachCalled);
        assertTrue(getFragment().onCreateCalled);

        assertFalse(getFragment().onCreateViewCalled);
        assertFalse(getFragment().onActivityCreatedCalled);
        assertFalse(getFragment().onCreateViewCalled);
        getFragmentInstrumentation().callFragmentOnActivityCreated();
        assertTrue(getFragment().onCreateViewCalled);
        assertTrue(getFragment().onCreateViewCalled);
        assertTrue(getFragment().onActivityCreatedCalled);

        assertNull(getFragment().getView());

        assertFalse(getFragment().onStartCalled);
        getFragmentInstrumentation().callFragmentOnStart();
        assertTrue(getFragment().onStartCalled);

        assertFalse(getFragment().onResumeCalled);
        getFragmentInstrumentation().callFragmentOnResume();
        assertTrue(getFragment().onResumeCalled);

        // Now fragment is active

        // Deactivate fragment

        assertFalse(getFragment().onPauseCalled);
        getFragmentInstrumentation().callFragmentOnPause();
        assertTrue(getFragment().onPauseCalled);

        assertFalse(getFragment().onStopCalled);
        getFragmentInstrumentation().callFragmentOnStop();
        assertTrue(getFragment().onStopCalled);
    }

    public void testThatFragmentManagerObtained() {
        assertNotNull(getFragmentManager());
    }

    public void testThatFragmentManagerObtainedAfterSetActivityCalled() {
        setActivity(new FragmentActivity());
        assertNotNull(getFragmentManager());
    }

    public void testThatFragmentManagerObtainedAfterStartFragmentCalled() {
        startFragment(null, null, null);
        assertNotNull(getFragmentManager());
    }

}
