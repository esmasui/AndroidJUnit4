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
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManagerImplTrojanHorse;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class FragmentInstrumentation {

    private FragmentManagerImplTrojanHorse mfragmentManager;

    private void assertFragmentManager() {
        if (mfragmentManager == null) {
            throw new IllegalStateException("FragmentManager is null");
        }
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        mfragmentManager = FragmentManagerImplTrojanHorse.create(fragmentManager);
    }

    public void callFragmentOnActivityCreated() {
        assertFragmentManager();
        mfragmentManager.dispatchActivityCreated();
    }

    public void callFragmentOnConfigurationChanged(Configuration configuration) {
        assertFragmentManager();
        mfragmentManager.dispatchConfigurationChanged(configuration);
    }

    public void callFragmentOnContextItemSelected(MenuItem item) {
        assertFragmentManager();
        mfragmentManager.dispatchContextItemSelected(item);
    }

    public void callFragmentOnCreate() {
        assertFragmentManager();
        mfragmentManager.dispatchCreate();
    }

    public void callFragmentOnCreateOptionsMenu(Menu menu,
                                                MenuInflater menuInflater) {
        assertFragmentManager();
        mfragmentManager.dispatchCreateOptionsMenu(menu,
                                                   menuInflater);
    }

    public void callFragmentOnDestroy() {
        assertFragmentManager();
        mfragmentManager.dispatchDestroy();
    }

    public void callFragmentOnLowMemory() {
        assertFragmentManager();
        mfragmentManager.dispatchLowMemory();
    }

    public void callFragmentOnOptionsItemSelected(MenuItem item) {
        assertFragmentManager();
        mfragmentManager.dispatchOptionsItemSelected(item);
    }

    public void callFragmentOnOptionsMenuClosed(Menu menu) {
        assertFragmentManager();
        mfragmentManager.dispatchOptionsMenuClosed(menu);
    }

    public void callFragmentOnPause() {
        assertFragmentManager();
        mfragmentManager.dispatchPause();
    }

    public void callFragmentOnPrepareOptionsMenu(Menu menu) {
        assertFragmentManager();
        mfragmentManager.dispatchPrepareOptionsMenu(menu);
    }

    public void callFragmentOnReallyStop() {
        assertFragmentManager();
        mfragmentManager.dispatchReallyStop();
    }

    public void callFragmentOnResume() {
        assertFragmentManager();
        mfragmentManager.dispatchResume();
    }

    public void callFragmentOnStart() {
        assertFragmentManager();
        mfragmentManager.dispatchStart();
    }

    public void callFragmentOnStop() {
        assertFragmentManager();
        mfragmentManager.dispatchStop();
    }
}
