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

import java.util.List;

import junit.framework.JUnit4TestAdapter;
import junit.framework.JUnit4TestAdapterCache;
import junit.framework.Test;
import junit.framework.TestResult;

import org.junit.Ignore;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.manipulation.Sorter;

import com.uphyca.testing.junit.internal.requests.AndroidClassRequest;

public class AndroidJUnit4TestAdapter extends JUnit4TestAdapter {
    private final Class<?> fNewTestClass;

    private final Runner fRunner;

    private final JUnit4TestAdapterCache fCache;

    public AndroidJUnit4TestAdapter(Class<?> newTestClass) {
        this(newTestClass, JUnit4TestAdapterCache.getDefault());
    }

    public AndroidJUnit4TestAdapter(final Class<?> newTestClass,
            JUnit4TestAdapterCache cache) {
        super(newTestClass, cache);
        fCache = cache;
        fNewTestClass = newTestClass;
        //For Android
        fRunner = new AndroidClassRequest(newTestClass, false).getRunner();
    }

    public int countTestCases() {
        return fRunner.testCount();
    }

    public void run(TestResult result) {
        fRunner.run(fCache.getNotifier(result, this));
    }

    // reflective interface for Eclipse
    public List<Test> getTests() {
        return fCache.asTestList(getDescription());
    }

    // reflective interface for Eclipse
    public Class<?> getTestClass() {
        return fNewTestClass;
    }
    
    public Description getDescription() {
        Description description= fRunner.getDescription();      
        return removeIgnored(description);
    }

    private Description removeIgnored(Description description) {
        if (isIgnored(description))
            return Description.EMPTY;
        Description result = description.childlessCopy();
        for (Description each : description.getChildren()) {
            Description child= removeIgnored(each);
            if (! child.isEmpty())
                result.addChild(child);
        }
        return result;
    }

    private boolean isIgnored(Description description) {
        return description.getAnnotation(Ignore.class) != null;
    }

    @Override
    public String toString() {
        return fNewTestClass.getName();
    }

    public void filter(Filter filter) throws NoTestsRemainException {
        filter.apply(fRunner);
    }

    public void sort(Sorter sorter) {
        sorter.apply(fRunner);
    }
}