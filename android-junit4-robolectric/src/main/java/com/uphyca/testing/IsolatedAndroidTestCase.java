package com.uphyca.testing;

import android.content.Context;

/**
 * Extend this if you need to access Resources or other things that depend on Activity Context.
 * 
 * This class provids a mock context via getMockContext() method which prevents its users from talking to the rest of the device while
 * stubbing enough methods to satify code that tries to talk to other packages.
 */
public class IsolatedAndroidTestCase extends AndroidTestCase {

    /**
     * Gets the isolated context created by this class during initialization.
     * @return The isolated context instance
     */
    protected Context getMockContext() {
        return getContext();
    }
}
