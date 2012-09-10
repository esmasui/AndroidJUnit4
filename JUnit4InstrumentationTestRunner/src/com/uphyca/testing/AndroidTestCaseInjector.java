package com.uphyca.testing;

import android.content.Context;
import android.os.Build;
import android.test.AndroidTestCase;

abstract class AndroidTestCaseInjector {

    abstract void setTestContext(AndroidTestCase test,
                                 Context context);

    abstract Context getTestContext(AndroidTestCase test);

    private static final AndroidTestCaseInjector sInjector = ensureInstance();

    private static final AndroidTestCaseInjector ensureInstance() {
        @SuppressWarnings("deprecation")
        final int sdkVersion = Integer.parseInt(Build.VERSION.SDK);
        if (sdkVersion < Build.VERSION_CODES.ECLAIR) {
            return new CupcakeAndroidTestCaseInjector();
        } else {
            return new EclairAndroidTestCaseInjector();
        }
    }

    static AndroidTestCaseInjector getInstance() {
        return sInjector;
    }

    private static final class CupcakeAndroidTestCaseInjector extends AndroidTestCaseInjector {

        @Override
        void setTestContext(AndroidTestCase test,
                            Context context) {
            // noop.
        }

        @Override
        Context getTestContext(AndroidTestCase test) {
            return null;
        }
    }

    private static final class EclairAndroidTestCaseInjector extends AndroidTestCaseInjector {

        @Override
        void setTestContext(AndroidTestCase test,
                            Context context) {
            test.setTestContext(context);
        }

        @Override
        Context getTestContext(AndroidTestCase test) {
            return test.getTestContext();
        }
    }
}
