package com.uphyca.testing;

import junit.framework.JUnit4TestAdapterCache;
import junit.framework.TestResult;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

class JUnit4TestClassAdaptingListener extends RunListener {

    private TestResult _result;
    private JUnit4TestAdapterCache _cache;

    public JUnit4TestClassAdaptingListener(TestResult result,
                                           JUnit4TestAdapterCache cache) {
        this._result = result;
        this._cache = cache;
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        _result.addError(_cache.asTest(failure.getDescription()),
                         failure.getException());
    }

    @Override
    public void testFinished(Description description) throws Exception {
        _result.endTest(_cache.asTest(description));
    }

    @Override
    public void testStarted(Description description) throws Exception {
        _result.startTest(_cache.asTest(description));
    }

}
