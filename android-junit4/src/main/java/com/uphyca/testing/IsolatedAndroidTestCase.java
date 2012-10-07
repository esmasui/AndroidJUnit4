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

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.test.IsolatedContext;
import android.test.RenamingDelegatingContext;
import android.test.mock.MockContentResolver;
import android.test.mock.MockContext;

/**
 * Extend this if you need to access Resources or other things that depend on Activity Context.
 * 
 * This class provids a mock context via getMockContext() method which prevents its users from talking to the rest of the device while
 * stubbing enough methods to satify code that tries to talk to other packages.
 */
public class IsolatedAndroidTestCase extends AndroidTestCase {

    private IsolatedContext _isolatedContext;
    private Set<SQLiteDatabase> _openedDataabses;

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
            return getMockContext();
        }
    }

    private class RenamingDelegatingContext2 extends RenamingDelegatingContext {

        public RenamingDelegatingContext2(Context context,
                                          Context fileContext,
                                          String filePrefix) {
            super(context,
                  fileContext,
                  filePrefix);
        }

        @Override
        public SQLiteDatabase openOrCreateDatabase(String name,
                                                   int mode,
                                                   CursorFactory factory) {
            SQLiteDatabase sqlite = super.openOrCreateDatabase(name, mode, factory);
            _openedDataabses.add(sqlite);
            return sqlite;
        }

        @SuppressLint("NewApi")
        @Override
        public SQLiteDatabase openOrCreateDatabase(String name,
                                                   int mode,
                                                   CursorFactory factory,
                                                   DatabaseErrorHandler errorHandler) {

            SQLiteDatabase sqlite = super.openOrCreateDatabase(name, mode, factory, errorHandler);
            _openedDataabses.add(sqlite);
            return sqlite;
        }

    }

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        _openedDataabses = new HashSet<SQLiteDatabase>();
        final String filenamePrefix = "test.";
        RenamingDelegatingContext targetContextWrapper = new RenamingDelegatingContext2(new MockContext2(),
                                                                                        getContext(),
                                                                                        filenamePrefix);
        _isolatedContext = new IsolatedContext(new MockContentResolver(),
                                               targetContextWrapper);
    }

    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        for (SQLiteDatabase each : _openedDataabses) {
            if (each.isOpen()) {
                each.close();
            }
        }
    }

    /**
     * Gets the isolated context created by this class during initialization.
     * @return The isolated context instance
     */
    protected IsolatedContext getMockContext() {
        return _isolatedContext;
    }
}
