package com.uphyca.testing;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.test.AndroidTestCase;

public abstract class AndroidTestCaseMethodInvoker {

    public abstract void assertActivityRequiresPermission(AndroidTestCase test,
                                                   String packageName,
                                                   String className,
                                                   String permission);

    public abstract void assertReadingContentUriRequiresPermission(AndroidTestCase test,
                                                            Uri uri,
                                                            String permission);

    public abstract void assertWritingContentUriRequiresPermission(AndroidTestCase test,
                                                            Uri uri,
                                                            String permission);

    private static final AndroidTestCaseMethodInvoker sInvoker = ensureInstance();

    private static final AndroidTestCaseMethodInvoker ensureInstance() {
        @SuppressWarnings("deprecation")
        final int sdkVersion = Integer.parseInt(Build.VERSION.SDK);
        if (sdkVersion < Build.VERSION_CODES.DONUT) {
            return new CupcakeAndroidTestCaseMethodInvoker();
        } else {
            return new DonutAndroidTestCaseMethodInvoker();
        }
    }

    public static AndroidTestCaseMethodInvoker getInstance() {
        return sInvoker;
    }

    private static final class DonutAndroidTestCaseMethodInvoker extends AndroidTestCaseMethodInvoker {

        
        @TargetApi(4)
        @Override
        public void assertActivityRequiresPermission(AndroidTestCase test,
                                              String packageName,
                                              String className,
                                              String permission) {

            test.assertActivityRequiresPermission(packageName, className, permission);
        }

        
        @TargetApi(4)
        @Override
        public void assertReadingContentUriRequiresPermission(AndroidTestCase test,
                                                       Uri uri,
                                                       String permission) {
            test.assertReadingContentUriRequiresPermission(uri, permission);
        }


        @TargetApi(4)
        @Override
        public void assertWritingContentUriRequiresPermission(AndroidTestCase test,
                                                       Uri uri,
                                                       String permission) {
            test.assertWritingContentUriRequiresPermission(uri, permission);
        }
    }

    private static final class CupcakeAndroidTestCaseMethodInvoker extends AndroidTestCaseMethodInvoker {

        @Override
        public void assertActivityRequiresPermission(AndroidTestCase test,
                                              String packageName,
                                              String className,
                                              String permission) {
            // noop.
        }

        @Override
        public void assertReadingContentUriRequiresPermission(AndroidTestCase test,
                                                       Uri uri,
                                                       String permission) {
            // noop.
        }

        @Override
        public void assertWritingContentUriRequiresPermission(AndroidTestCase test,
                                                       Uri uri,
                                                       String permission) {
            // noop.
        }
    }
}
