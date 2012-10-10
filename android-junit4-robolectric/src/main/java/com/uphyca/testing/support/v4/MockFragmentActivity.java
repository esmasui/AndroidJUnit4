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
package com.uphyca.testing.support.v4;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * A mock {@link android.support.v4.app.FragmentActivity} class.  All methods are non-functional and throw 
 * {@link java.lang.UnsupportedOperationException}.  Override it as necessary to provide the 
 * operations that you need.
 */
public class MockFragmentActivity extends FragmentActivity {

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void onDestroy() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void onPause() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void onResume() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void onStart() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void onStop() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }
}
