package com.uphyca.testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.content.ContentProvider;
import android.content.Context;
import android.net.Uri;
import android.test.IsolatedContext;
import android.test.mock.MockContentResolver;

public abstract class ProviderTestCase2<T extends ContentProvider> {

    private final ProviderTester2<T> _tester;

    public ProviderTestCase2(Class<T> providerClass,
                                   String providerAuthority) {
        _tester = new ProviderTester2<T>(this, providerClass, providerAuthority);
    }

    @Before
    public void setUp() throws Exception {
        _tester.setUp();
    }

    @After
    public void tearDown() throws Exception {
        _tester.tearDown();
    }

    @Test
    public void testAndroidTestCaseSetupProperly() {
        _tester.testAndroidTestCaseSetupProperly();
    }

    /**
     * @param context
     * @see android.test.AndroidTestCase#setContext(android.content.Context)
     */
    public void setContext(Context context) {
        _tester.setContext(context);
    }

    /**
     * @return
     * @see android.test.AndroidTestCase#getContext()
     */
    public Context getContext() {
        return _tester.getContext();
    }

    /**
     * @param packageName
     * @param className
     * @param permission
     * @see android.test.AndroidTestCase#assertActivityRequiresPermission(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public void assertActivityRequiresPermission(String packageName,
                                                 String className,
                                                 String permission) {
        AndroidTestCaseMethodInvoker invoker = AndroidTestCaseMethodInvoker.getInstance();
        invoker.assertActivityRequiresPermission(_tester, packageName, className, permission);
    }

    /**
     * @param uri
     * @param permission
     * @see android.test.AndroidTestCase#assertReadingContentUriRequiresPermission(android.net.Uri,
     *      java.lang.String)
     */
    public void assertReadingContentUriRequiresPermission(Uri uri,
                                                          String permission) {
        AndroidTestCaseMethodInvoker invoker = AndroidTestCaseMethodInvoker.getInstance();
        invoker.assertReadingContentUriRequiresPermission(_tester, uri, permission);
    }

    /**
     * @param uri
     * @param permission
     * @see android.test.AndroidTestCase#assertWritingContentUriRequiresPermission(android.net.Uri,
     *      java.lang.String)
     */
    public void assertWritingContentUriRequiresPermission(Uri uri,
                                                          String permission) {
        AndroidTestCaseMethodInvoker invoker = AndroidTestCaseMethodInvoker.getInstance();
        invoker.assertWritingContentUriRequiresPermission(_tester, uri, permission);
    }

    /**
     * @return
     * @see android.test.ProviderTestCase2#getProvider()
     */
    public T getProvider() {
        return _tester.getProvider();
    }

    /**
     * @return
     * @see android.test.ProviderTestCase2#getMockContentResolver()
     */
    public MockContentResolver getMockContentResolver() {
        return _tester.getMockContentResolver();
    }

    /**
     * @return
     * @see android.test.ProviderTestCase2#getMockContext()
     */
    public IsolatedContext getMockContext() {
        return _tester.getMockContext();
    }

    /**
     * @param testCaseClass
     * @throws IllegalAccessException
     * @see com.uphyca.testing.ProviderTester2#scrubClass(java.lang.Class)
     */
    protected void scrubClass(Class<?> testCaseClass) throws IllegalAccessException {
        _tester.scrubClass(testCaseClass);
    }
}
