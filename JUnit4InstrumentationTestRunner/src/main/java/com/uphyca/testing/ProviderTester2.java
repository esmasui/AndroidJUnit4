package com.uphyca.testing;

import android.content.ContentProvider;
import android.test.ProviderTestCase2;

class ProviderTester2<T extends ContentProvider> extends ProviderTestCase2<T> {

    public ProviderTester2(Object owner,
                                 Class<T> providerClass,
                                 String providerAuthority) {
        super(providerClass, providerAuthority);
        Infrastructure.setContextToAndroidTestCase(this);
        Infrastructure.setPerformanceWriterIfPerformanceCollectorTestCase(owner);
    }

    /* (non-Javadoc)
     * @see android.test.ProviderTestCase2#setUp()
     */
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    /* (non-Javadoc)
     * @see android.test.ProviderTestCase2#tearDown()
     */
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /* (non-Javadoc)
     * @see android.test.AndroidTestCase#scrubClass(java.lang.Class)
     */
    @Override
    public void scrubClass(Class<?> testCaseClass) throws IllegalAccessException {
        super.scrubClass(testCaseClass);
    }
}
