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
