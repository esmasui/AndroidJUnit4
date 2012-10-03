package com.uphyca.testing;

import android.app.Activity;

import com.uphyca.testing.proxy.android.app.ActivityProxy;
import com.uphyca.testing.tester.android.app.DelegatingActivityProxy;

public abstract class Proxies {

    public static ActivityProxy proxyOf(Activity activity) {
        return DelegatingActivityProxy.create(activity);
    }
}
