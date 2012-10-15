package com.uphyca.testing.shadows;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.xtremelabs.robolectric.internal.Implementation;
import com.xtremelabs.robolectric.internal.Implements;
import com.xtremelabs.robolectric.shadows.ShadowFragment;

@Implements(Fragment.class)
public class CustomShadowFragment extends ShadowFragment {

    @Implementation
    public void startActivity(Intent intent) {
        activity.startActivity(intent);
    }
}
