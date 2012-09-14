/*
 * Copyright (C) 2012 uPhyca Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
