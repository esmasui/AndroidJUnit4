/*
 * Copyright (C) 2008 The Android Open Source Project
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

package com.uphyca.testing.android.suitebuilder;

import static com.uphyca.testing.android.suitebuilder.JUnit4TestGrouping.SORT_BY_FULLY_QUALIFIED_NAME;
import static com.uphyca.testing.android.suitebuilder.JUnit4TestPredicates.REJECT_SUPPRESSED;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;
import org.junit.runner.Computer;
import org.junit.runner.Description;
import org.junit.runner.Request;
import org.junit.runner.Runner;
import org.junit.runner.manipulation.Filter;
import org.junit.runners.model.InitializationError;

import android.content.Context;

import com.android.internal.util.Predicate;
import com.google.android.collect.Lists;
import com.uphyca.testing.AndroidParallelComputer;
import com.uphyca.testing.junit.internal.builders.AndroidAllDefaultPossibilitiesBuilder;

/**
 * Modified version of android.test.suitebuilder.TestSuiteBuilder.
 * 
 * Build suites based on a combination of included packages, excluded packages,
 * and predicates that must be satisfied.
 */
public class JUnit4TestSuiteBuilder {

    private Context context;
    private final JUnit4TestGrouping testGrouping = new JUnit4TestGrouping(SORT_BY_FULLY_QUALIFIED_NAME);
    private final Set<Predicate<Description>> predicates = new HashSet<Predicate<Description>>();
    private String currentClassname;
    private String suiteName;
    private Class<?>[] testSuite;

    private List<Description> descriptions = Lists.newArrayList();

    /**
     * The given name is automatically prefixed with the package containing the tests to be run.
     * If more than one package is specified, the first is used.
     *
     * @param clazz Use the class from your .apk. Use the class name for the test suite name.
     *              Use the class' classloader in order to load classes for testing.
     *              This is needed when running in the emulator.
     */
    public JUnit4TestSuiteBuilder(Class<?> clazz) {
        this(clazz.getName(), clazz.getClassLoader());
    }

    public JUnit4TestSuiteBuilder(String name,
                                  ClassLoader classLoader) {
        this.suiteName = name;
        this.testGrouping.setClassLoader(classLoader);

        addRequirements(REJECT_SUPPRESSED);
    }

    /** @hide pending API Council approval */
    public JUnit4TestSuiteBuilder addTestClassByName(String testClassName,
                                                     String testMethodName,
                                                     Context context) {

        Description desc;
        if (testMethodName == null) {
            desc = Description.createSuiteDescription(loadTestClass(context,
                                                                    testClassName));
        } else {
            desc = Description.createTestDescription(loadTestClass(context,
                                                                   testClassName),
                                                     testMethodName);
        }

        this.descriptions.add(desc);

        return this;
    }

    private Class<?> loadTestClass(Context context,
                                   String testClassName) {
        try {
            return (Class<?>) context.getClassLoader().loadClass(testClassName);
        } catch (ClassNotFoundException e) {
            runFailed("Could not find test class. Class: " + testClassName);
        }
        return null;
    }

    protected void runFailed(String message) {
        throw new RuntimeException(message);
    }

    /**
     * Include all tests that satisfy the requirements in the given packages and all sub-packages,
     * unless otherwise specified.
     *
     * @param packageNames Names of packages to add.
     * @return The builder for method chaining.
     */
    public JUnit4TestSuiteBuilder includePackages(String... packageNames) {
        testGrouping.addPackagesRecursive(packageNames);
        return this;
    }

    /**
     * Exclude all tests in the given packages and all sub-packages, unless otherwise specified.
     *
     * @param packageNames Names of packages to remove.
     * @return The builder for method chaining.
     */
    public JUnit4TestSuiteBuilder excludePackages(String... packageNames) {
        testGrouping.removePackagesRecursive(packageNames);
        return this;
    }

    /**
     * Exclude tests that fail to satisfy all of the given predicates.
     *
     * @param predicates Predicates to add to the list of requirements.
     * @return The builder for method chaining.
     */
    public JUnit4TestSuiteBuilder addRequirements(List<Predicate<Description>> predicates) {
        this.predicates.addAll(predicates);
        return this;
    }

    /**
     * Include all junit tests that satisfy the requirements in the calling class' package and all
     * sub-packages.
     *
     * @return The builder for method chaining.
     */
    public final JUnit4TestSuiteBuilder includeAllPackagesUnderHere() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        String callingClassName = null;
        String thisClassName = JUnit4TestSuiteBuilder.class.getName();

        // We want to get the package of this method's calling class. This method's calling class
        // should be one level below this class in the stack trace.
        for (int i = 0; i < stackTraceElements.length; i++) {
            StackTraceElement element = stackTraceElements[i];
            if (thisClassName.equals(element.getClassName()) && "includeAllPackagesUnderHere".equals(element.getMethodName())) {
                // We've found this class in the call stack. The calling class must be the
                // next class in the stack.
                callingClassName = stackTraceElements[i + 1].getClassName();
                break;
            }
        }

        String packageName = parsePackageNameFromClassName(callingClassName);
        return includePackages(packageName);
    }

    /**
     * Override the default name for the suite being built. This should generally be called if you
     * call {@link #addRequirements(com.android.internal.util.Predicate[])} to make it clear which
     * tests will be included. The name you specify is automatically prefixed with the package
     * containing the tests to be run. If more than one package is specified, the first is used.
     *
     * @param newSuiteName Prefix of name to give the suite being built.
     * @return The builder for method chaining.
     */
    public JUnit4TestSuiteBuilder named(String newSuiteName) {
        suiteName = newSuiteName;
        return this;
    }

    /**
     * Call this method once you've configured your builder as desired.
     *
     * @return The suite containing the requested tests.
     */
    public final Request build() {
        List<Class<?>> testCaseClasses = Lists.newArrayList();

        //FIXME Supports following options
        // - Filter test run to tests with given annotation: adb shell am instrument -w -e annotation com.android.foo.MyAnnotation com.android.foo/android.test.InstrumentationTestRunner
        // - Run the instrumentation using "adb shell am instrument -w", with no optional arguments, to run all tests (except performance tests).
        // - Run the instrumentation using "adb shell am instrument -w", with the argument '-e func true' to run all functional tests. These are tests that derive from InstrumentationTestCase.
        // - Run the instrumentation using "adb shell am instrument -w", with the argument '-e unit true' to run all unit tests. These are tests that do notderive from InstrumentationTestCase (and are not performance tests).

        Filter filters = null;

        if (testSuite != null) {
            for (Class<?> clazz : testSuite) {
                testCaseClasses.add(clazz);
                //                Filter newFilter = Filters.matchSuiteDescription(Description.createSuiteDescription(clazz));
                //                if (filters == null) {
                //                    filters = newFilter;
                //                } else {
                //                    filters = Filters.union(filters, newFilter);
                //                }
            }
        }

        for (Class<?> clazz : testGrouping.getTestCaseClasses()) {
            testCaseClasses.add(clazz);
            //            Filter newFilter = Filters.matchSuiteDescription(Description.createSuiteDescription(clazz));
            //            if (filters == null) {
            //                filters = newFilter;
            //            } else {
            //                filters = Filters.union(filters, newFilter);
            //            }
        }

        for (Description desc : descriptions) {
            testCaseClasses.add(desc.getTestClass());
            //            //if (desc.isTest() || desc.isSuite()) {
            //                Filter newFilter = (desc.getMethodName() != null) ? Filter.matchMethodDescription(desc) : Filters.matchSuiteDescription(desc);
            //                if (filters == null) {
            //                    filters = newFilter;
            //                } else {
            //                    filters = Filters.union(filters, newFilter);
            //                }
            //            //}
        }

        //FIXME Supports subclasses of Computer such as ParallelComputer.
        Request req = classes(Computer.serial(),
                              testCaseClasses.toArray(new Class[testCaseClasses.size()]));

        Filter requirements = new Filter() {
            @Override
            public boolean shouldRun(Description description) {
                if (description.isTest()) {
                    return satisfiesAllPredicates(description);
                }
                // explicitly check if any children want to run
                for (Description each : description.getChildren())
                    if (shouldRun(each))
                        return true;
                return false;
            }

            @Override
            public String describe() {
                return "requirements";
            }
        };

        if (filters == null) {
            filters = requirements;
        } else {
            filters = filters.intersect(requirements);
        }

        req = req.filterWith(filters);

        return req;
    }

    /**
     * Create a <code>Request</code> that, when processed, will run all the tests
     * in a set of classes.
     * @param computer Helps construct Runners from classes
     * @param classes the classes containing the tests
     * @return a <code>Request</code> that will cause all tests in the classes to be run
     */
    public Request classes(Computer computer,
                           Class<?>... classes) {
        try {
            //For Android
            AllDefaultPossibilitiesBuilder builder = new AndroidAllDefaultPossibilitiesBuilder(true);
            Runner suite = computer.getSuite(builder,
                                             classes);
            return Request.runner(suite);
        } catch (InitializationError e) {
            throw new RuntimeException("Bug in saff's brain: Suite constructor, called as above, should always complete");
        }
    }

    /**
     * Subclasses use this method to determine the name of the suite.
     *
     * @return The package and suite name combined.
     */
    protected String getSuiteName() {
        return suiteName;
    }

    /**
     * Exclude tests that fail to satisfy all of the given predicates. If you call this method, you
     * probably also want to call {@link #named(String)} to override the default suite name.
     *
     * @param predicates Predicates to add to the list of requirements.
     * @return The builder for method chaining.
     */
    public final JUnit4TestSuiteBuilder addRequirements(Predicate<Description>... predicates) {
        ArrayList<Predicate<Description>> list = new ArrayList<Predicate<Description>>();
        Collections.addAll(list,
                           predicates);
        return addRequirements(list);
    }

    // FIXME Use FailedToCreateTests.
    //    /**
    //     * A special {@link junit.framework.TestCase} used to indicate a failure during the build()
    //     * step.
    //     */
    //    public static class FailedToCreateTests extends TestCase {
    //        private final Exception exception;
    //
    //        public FailedToCreateTests(Exception exception) {
    //            super("testSuiteConstructionFailed");
    //            this.exception = exception;
    //        }
    //
    //        public void testSuiteConstructionFailed() {
    //            throw new RuntimeException("Exception during suite construction", exception);
    //        }
    //    }

    /**
     * @return the test package that represents the packages that were included for our test suite.
     *
     * {@hide} Not needed for 1.0 SDK.
     */
    protected JUnit4TestGrouping getTestGrouping() {
        return testGrouping;
    }

    private boolean satisfiesAllPredicates(Description test) {
        for (Predicate<Description> predicate : predicates) {
            if (!predicate.apply(test)) {
                return false;
            }
        }
        return true;
    }

    private static String parsePackageNameFromClassName(String className) {
        return className.substring(0,
                                   className.lastIndexOf('.'));
    }

    public void addTestSuite(Class<?>[] testSuite) {
        this.testSuite = testSuite;
    }
}
