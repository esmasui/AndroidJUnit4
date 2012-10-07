package com.uphyca.testing;

import org.junit.Before;

import android.test.IsolatedContext;
import android.test.mock.MockContentResolver;

/**
 * Extend this if you need to access Resources or other things that depend on Activity Context.
 * 
 * This class provids a mock context via getMockContext() method which prevents its users from talking to the rest of the device while
 * stubbing enough methods to satify code that tries to talk to other packages.
 */
public class IsolatedAndroidTestCase extends AndroidTestCase {

    private IsolatedContext mMockContext;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        mMockContext = new IsolatedContext(new MockContentResolver(),
                                           getContext());
    }

    /**
     * Gets the isolated context created by this class during initialization.
     * @return The isolated context instance
     */
    protected IsolatedContext getMockContext() {
        return mMockContext;
    }
}
