/*
 * Copyright (C) 2012 uPhyca Inc. http://www.uphyca.com/
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
 * limitations under the License
 */
package com.uphyca.testing.support.v4;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.shadows.ShadowFragment;
import com.xtremelabs.robolectric.tester.android.util.TestFragmentManager;

public class FragmentInstrumentation {

    private TestFragmentManager mfragmentManager;

    private void assertFragmentManager() {
        if (mfragmentManager == null) {
            throw new IllegalStateException("FragmentManager is null");
        }
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        mfragmentManager = (TestFragmentManager) fragmentManager;
    }

    public void callFragmentOnActivityCreated() {
        assertFragmentManager();
        for (Fragment each : mfragmentManager.getFragments()
                                             .values()) {
            ShadowFragment shadow = Robolectric.shadowOf(each);
            Bundle savedInstanceState = shadow.getSavedInstanceState();

            View view = each.onCreateView(each.getActivity()
                                              .getLayoutInflater(), null, savedInstanceState);
            shadow.setView(view);

            each.onViewCreated(view, null);
            each.onActivityCreated(savedInstanceState);
        }
    }

    public void callFragmentOnConfigurationChanged(Configuration configuration) {
        assertFragmentManager();
        for (Fragment each : mfragmentManager.getFragments()
                                             .values()) {
            each.onConfigurationChanged(configuration);
        }
    }

    public void callFragmentOnContextItemSelected(MenuItem item) {
        assertFragmentManager();
        for (Fragment each : mfragmentManager.getFragments()
                                             .values()) {
            each.onContextItemSelected(item);
        }
    }

    public void callFragmentOnCreate() {
        assertFragmentManager();
        for (Fragment each : mfragmentManager.getFragments()
                                             .values()) {
            ShadowFragment shadow = Robolectric.shadowOf(each);
            Bundle savedInstanceState = shadow.getSavedInstanceState();
            each.onCreate(savedInstanceState);
        }
    }

    public void callFragmentOnCreateOptionsMenu(Menu menu,
                                                MenuInflater menuInflater) {
        assertFragmentManager();
        for (Fragment each : mfragmentManager.getFragments()
                                             .values()) {
            each.onCreateOptionsMenu(menu, menuInflater);
        }
    }

    public void callFragmentOnDestroy() {
        assertFragmentManager();
        for (Fragment each : mfragmentManager.getFragments()
                                             .values()) {
            each.onDestroy();
            each.onDetach();
        }
    }

    public void callFragmentOnLowMemory() {
        assertFragmentManager();
        for (Fragment each : mfragmentManager.getFragments()
                                             .values()) {
            each.onLowMemory();
        }
    }

    public void callFragmentOnOptionsItemSelected(MenuItem item) {
        assertFragmentManager();
        for (Fragment each : mfragmentManager.getFragments()
                                             .values()) {
            each.onOptionsItemSelected(item);
        }
    }

    public void callFragmentOnOptionsMenuClosed(Menu menu) {
        assertFragmentManager();
        for (Fragment each : mfragmentManager.getFragments()
                                             .values()) {
            each.onOptionsMenuClosed(menu);
        }
    }

    public void callFragmentOnPause() {
        assertFragmentManager();
        for (Fragment each : mfragmentManager.getFragments()
                                             .values()) {
            each.onPause();
        }
    }

    public void callFragmentOnPrepareOptionsMenu(Menu menu) {
        assertFragmentManager();
        for (Fragment each : mfragmentManager.getFragments()
                                             .values()) {
            each.onPrepareOptionsMenu(menu);
        }
    }

    public void callFragmentOnReallyStop() {
        assertFragmentManager();
        //TODO Implements this.
    }

    public void callFragmentOnResume() {
        assertFragmentManager();
        for (Fragment each : mfragmentManager.getFragments()
                                             .values()) {
            each.onResume();
        }
    }

    public void callFragmentOnStart() {
        assertFragmentManager();

        for (Fragment each : mfragmentManager.getFragments()
                                             .values()) {
            each.onStart();
        }
    }

    public void callFragmentOnStop() {
        assertFragmentManager();
        for (Fragment each : mfragmentManager.getFragments()
                                             .values()) {
            each.onStop();
        }
    }
}
