/**
 * 
 */
package com.uphyca.testing.junit.internal.builders;

import org.junit.internal.builders.JUnit3Builder;
import org.junit.runner.Runner;

import com.uphyca.testing.junit.internal.runners.AndroidJUnit38ClassRunner;

/**
 * Modified version of org.junit.internal.builders.JUnit3Builder.
 */
public class AndroidJUnit3Builder extends JUnit3Builder {
	@Override
	public Runner runnerForClass(Class<?> testClass) throws Throwable {
		if (isPre4Test(testClass)) {
		    //For Android
			return new AndroidJUnit38ClassRunner(testClass);
		}
		return null;
	}

	boolean isPre4Test(Class<?> testClass) {
		return junit.framework.TestCase.class.isAssignableFrom(testClass);
	}
}