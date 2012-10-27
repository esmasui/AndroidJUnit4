package com.uphyca.testing.shadows;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.internal.Implementation;
import com.xtremelabs.robolectric.internal.Implements;
import com.xtremelabs.robolectric.internal.RealObject;
import com.xtremelabs.robolectric.shadows.ShadowActivity;

@Implements(Activity.class)
public class CustomShadowActivity extends ShadowActivity {

    @RealObject
    Activity realActivity;

    @Implementation
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shadowOf(getApplication()).dispatchActivityCreated(realActivity, savedInstanceState);
    }

    @Implementation
    public void onStart() {
        shadowOf(getApplication()).dispatchActivityStarted(realActivity);
    }

    @Implementation
    public void onResume() {
        shadowOf(getApplication()).dispatchActivityResumed(realActivity);
    }

    @Implementation
    public void onPause() {
        shadowOf(getApplication()).dispatchActivityPaused(realActivity);
    }

    @Implementation
    public void onStop() {
        shadowOf(getApplication()).dispatchActivityStopped(realActivity);
    }

    @Implementation
    @Override
    public void onDestroy() {
        super.onDestroy();
        shadowOf(getApplication()).dispatchActivityDestroyed(realActivity);
    }

    @Implementation
    public void onSaveInstanceState(Bundle outState) {
        shadowOf(getApplication()).dispatchActivitySaveInstanceState(realActivity, outState);
    }

    public static CustomShadowApplication shadowOf(Application instance) {
        return (CustomShadowApplication) Robolectric.shadowOf_(instance);
    }
}
