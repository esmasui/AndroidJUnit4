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
package com.uphyca.testing.junit3.extension;

import java.io.File;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteOpenHelper;
import android.test.AndroidTestCase;
import android.test.IsolatedContext;
import android.test.RenamingDelegatingContext;
import android.test.mock.MockContentResolver;
import android.test.mock.MockContext;

/**
 * A convenience class for testing SQLiteOpenHelpers.
 */
public abstract class SQLiteOpenHelperTestCase<T extends SQLiteOpenHelper> extends AndroidTestCase {

    private Class<T> _openHelperClass;
    private T _openHelper;
    private IsolatedContext _openHelperContext;

    private class MockContext2 extends MockContext {
        @Override
        public Resources getResources() {
            return getContext().getResources();
        }

        @Override
        public File getDir(String name,
                           int mode) {
            String dirName = String.format("mockcontext2_%s", name);
            return getContext().getDir(dirName, mode);
        }

        @Override
        public Context getApplicationContext() {
            return this;
        }
    }

    public SQLiteOpenHelperTestCase(Class<T> openHelperClass) {
        _openHelperClass = openHelperClass;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        final String filenamePrefix = "test.";
        RenamingDelegatingContext targetContextWrapper = new RenamingDelegatingContext(new MockContext2(), getContext(), filenamePrefix);
        _openHelperContext = new IsolatedContext(new MockContentResolver(), targetContextWrapper);
        _openHelper = createSQLiteOpenHelper(_openHelperContext);
    }

    @Override
    protected void tearDown() throws Exception {
        _openHelper.close();
        super.tearDown();
    }

    /**
     * @return Returns the actual SQLiteOpenHelper under test.
     */
    public T getSQLiteOpenHelper() {
        return _openHelper;
    }

    protected T createSQLiteOpenHelper(Context context) throws Exception {
        return _openHelperClass.getConstructor(Context.class)
                               .newInstance(_openHelperContext);
    }

    protected Context getMockContext() {
        return _openHelperContext;
    }
}