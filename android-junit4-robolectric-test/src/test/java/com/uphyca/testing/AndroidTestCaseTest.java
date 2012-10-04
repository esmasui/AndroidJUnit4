package com.uphyca.testing;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;

import android.net.Uri;

@RunWith(AndroidTestRunner.class)
public class AndroidTestCaseTest extends AndroidTestCase {

    @Test
    public void assertPreconditions() {
        assertNotNull(getContext());
    }

    @Test
    public void shouldPassWhenAssertActivityRequiresPermission() {
        String packageName = null;
        String className = null;
        String permission = null;
        assertActivityRequiresPermission(packageName, className, permission);
    }

    @Test
    public void shouldPassWhenAssertReadingContentUriRequiresPermission() {
        Uri uri = null;
        String permission = null;
        assertReadingContentUriRequiresPermission(uri, permission);
    }

    @Test
    public void shouldPassAssertWritingContentUriRequiresPermission() {
        Uri uri = null;
        String permission = null;
        assertWritingContentUriRequiresPermission(uri, permission);
    }

    private Class<?> sClass;

    @Test
    public void shouldClearStaticFieldsWhenScrubClassCalled() throws IllegalAccessException {
        assertNull(sClass);
        sClass = getClass();

        scrubClass(getClass());
        assertNull(sClass);
    }
}
