package com.uphyca.testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Loader;
import android.net.Uri;

/**
 * @see android.test.LoaderTestCase
 */
@TargetApi(11)
public class LoaderTestCase {

    private final LoaderTester _tester;

    public LoaderTestCase() {
        _tester = new LoaderTester(this);
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
     * @param loader
     * @return
     * @see android.test.LoaderTestCase#getLoaderResultSynchronously(android.content.Loader)
     */
    public <T> T getLoaderResultSynchronously(Loader<T> loader) {
        return _tester.getLoaderResultSynchronously(loader);
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
     * @param testCaseClass
     * @throws IllegalAccessException
     * @see com.uphyca.testing.LoaderTester#scrubClass(java.lang.Class)
     */
    protected void scrubClass(Class<?> testCaseClass) throws IllegalAccessException {
        _tester.scrubClass(testCaseClass);
    }
}
