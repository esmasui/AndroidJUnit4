package com.uphyca.testing;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.uphyca.testing.proxy.android.app.ActivityProxy;
import com.uphyca.testing.proxy.android.support.v4.app.FragmentProxy;
import com.uphyca.testing.tester.android.app.DelegatingActivityProxy;
import com.uphyca.testing.tester.android.support.v4.app.DelegatingFragmentProxy;

public abstract class Proxies {

    public static ActivityProxy proxyOf(Activity activity) {
        return DelegatingActivityProxy.create(activity);
    }

    public static FragmentProxy proxyOf(Fragment fragment) {
        return DelegatingFragmentProxy.create(fragment);
    }
}
