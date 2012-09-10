/**
 * 
 */
package com.uphyca.testing.junit.internal.builders;

import org.junit.internal.builders.JUnit4Builder;
import org.junit.runner.Runner;

import com.uphyca.testing.junit.runners.AndroidBlockJUnit4ClassRunner;

public class AndroidJUnit4Builder extends JUnit4Builder {
    @Override
    public Runner runnerForClass(Class<?> testClass) throws Throwable {
        return new AndroidBlockJUnit4ClassRunner(testClass);
    }
}