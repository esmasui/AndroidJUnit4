package com.uphyca.testing.junit.internal.requests;

import org.junit.internal.requests.ClassRequest;
import org.junit.runner.Runner;

import com.uphyca.testing.junit.internal.builders.AndroidAllDefaultPossibilitiesBuilder;

public class AndroidClassRequest extends ClassRequest {

    private final Class<?> fTestClass;

    private boolean fCanUseSuiteMethod;

    public AndroidClassRequest(Class<?> testClass,
                               boolean canUseSuiteMethod) {
        super(testClass, canUseSuiteMethod);
        fTestClass = testClass;
        fCanUseSuiteMethod = canUseSuiteMethod;
    }

    public AndroidClassRequest(Class<?> testClass) {
        this(testClass, true);
    }

    @Override
    public Runner getRunner() {
        // For Android
        return new AndroidAllDefaultPossibilitiesBuilder(fCanUseSuiteMethod).safeRunnerForClass(fTestClass);
    }
}
