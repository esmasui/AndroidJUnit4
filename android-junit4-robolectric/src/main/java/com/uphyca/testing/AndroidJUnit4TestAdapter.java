package com.uphyca.testing;

import junit.framework.JUnit4TestAdapter;
import junit.framework.JUnit4TestAdapterCache;

public class AndroidJUnit4TestAdapter extends JUnit4TestAdapter {

    public AndroidJUnit4TestAdapter(Class<?> newTestClass,
                                    JUnit4TestAdapterCache cache) {
        super(newTestClass, cache);
    }

    public AndroidJUnit4TestAdapter(Class<?> newTestClass) {
        super(newTestClass);
    }
}
