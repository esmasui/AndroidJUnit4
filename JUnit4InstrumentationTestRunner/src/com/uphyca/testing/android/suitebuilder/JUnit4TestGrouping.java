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

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import android.util.Log;

import com.android.internal.util.Predicate;
import com.uphyca.testing.android.ClassPathPackageInfo;
import com.uphyca.testing.android.ClassPathPackageInfoSource;
import com.uphyca.testing.android.PackageInfoSources;

/**
 * Modified version of android.test.suitebuilder.TestGrouping.
 * 
 * 
 * Represents a collection of test classes present on the classpath. You can add individual classes
 * or entire packages. By default sub-packages are included recursively, but methods are
 * provided to allow for arbitrary inclusion or exclusion of sub-packages. Typically a
 * {@link JUnit4TestGrouping} will have only one root package, but this is not a requirement.
 * 
 * {@hide} Not needed for 1.0 SDK.
 */
public class JUnit4TestGrouping {

    private static final String LOG_TAG = "TestGrouping";

    public static final Comparator<Class<?>> SORT_BY_FULLY_QUALIFIED_NAME
    = new SortByFullyQualifiedName();

    private SortedSet<Class<?>> testCaseClasses;

    protected String firstIncludedPackage = null;
    private ClassLoader classLoader;

    public JUnit4TestGrouping(Comparator<Class<?>> comparator) {
        testCaseClasses = new TreeSet<Class<?>>(comparator);
    }

    public SortedSet<Class<?>> getTestCaseClasses() {
        return testCaseClasses;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JUnit4TestGrouping other = (JUnit4TestGrouping) o;
        if (!this.testCaseClasses.equals(other.testCaseClasses)) {
            return false;
        }
        return this.testCaseClasses.comparator().equals(other.testCaseClasses.comparator());
    }

    public int hashCode() {
        return testCaseClasses.hashCode();
    }

    /**
     * Include all tests in the given packages and all their sub-packages, unless otherwise
     * specified. Each of the given packages must contain at least one test class, either directly
     * or in a sub-package.
     *
     * @param packageNames Names of packages to add.
     * @return The {@link JUnit4TestGrouping} for method chaining.
     */
    public JUnit4TestGrouping addPackagesRecursive(String... packageNames) {
        for (String packageName : packageNames) {
            List<Class<?>> addedClasses = testCaseClassesInPackage(packageName);
            if (addedClasses.isEmpty()) {
                Log.w(LOG_TAG, "Invalid Package: '" + packageName
                        + "' could not be found or has no tests");
            }
            testCaseClasses.addAll(addedClasses);
            if (firstIncludedPackage == null) {
                firstIncludedPackage = packageName;
            }
        }
        return this;
    }

    /**
     * Exclude all tests in the given packages and all their sub-packages, unless otherwise
     * specified.
     *
     * @param packageNames Names of packages to remove.
     * @return The {@link JUnit4TestGrouping} for method chaining.
     */
    public JUnit4TestGrouping removePackagesRecursive(String... packageNames) {
        for (String packageName : packageNames) {
            testCaseClasses.removeAll(testCaseClassesInPackage(packageName));
        }
        return this;
    }

    /**
     * @return The first package name passed to {@link #addPackagesRecursive(String[])}, or null
     *         if that method was never called.
     */
    public String getFirstIncludedPackage() {
        return firstIncludedPackage;
    }

    private List<Class<?>> testCaseClassesInPackage(String packageName) {
        ClassPathPackageInfoSource source = PackageInfoSources.forClassPath(classLoader);
        ClassPathPackageInfo packageInfo = source.getPackageInfo(packageName);

        Set<Class<?>> allClasses = packageInfo.getTopLevelClassesRecursive();

//FIXME Add nested classes to suite;
//        Set<Class<?>> allNestedClasses = Sets.newHashSet();
//        allNestedClasses.addAll(allClasses);
//        addNestedClasses(allClasses, allNestedClasses);
//        return selectTestClasses(allNestedClasses);
        
        return selectTestClasses(allClasses);
    }
    
    private void addNestedClasses(Set<Class<?>> allClasses, Set<Class<?>> result) {
        for (Class<?> c: allClasses) {
            addNestedClasses(c, result);
        }
    }
    
    private void addNestedClasses(Class<?> clazz, Set<Class<?>> result) {
        for (Class<?> c: clazz.getClasses()) {
            result.add(c);
            addNestedClasses(c, result);
        }
    }

    private List<Class<?>> selectTestClasses(Set<Class<?>> allClasses) {
        List<Class<?>> testClasses = new ArrayList<Class<?>>();
        for (Class<?> testClass : select(allClasses,
                new TestCasePredicate())) {
            testClasses.add((Class<?>) testClass);
        }
        return testClasses;
    }    

    private <T> List<T> select(Collection<T> items, Predicate<T> predicate) {
        ArrayList<T> selectedItems = new ArrayList<T>();
        for (T item : items) {
            if (predicate.apply(item)) {
                selectedItems.add(item);
            }
        }
        return selectedItems;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    /**
     * Sort classes by their fully qualified names (i.e. with the package
     * prefix).
     */
    private static class SortByFullyQualifiedName
            implements Comparator<Class<?>>, Serializable {

        public int compare(Class<?> class1,
                Class<?> class2) {
            return class1.getName().compareTo(class2.getName());
        }
    }
    
    private static class TestCasePredicate implements Predicate<Class<?>> {

        public boolean apply(Class<?> aClass) {
            //For JUnit3
            if (TestCase.class.isAssignableFrom((Class<?>) aClass)) {
                int modifiers = ((Class<?>) aClass).getModifiers();
                return Modifier.isPublic(modifiers)
                        && !Modifier.isAbstract(modifiers)
                        && hasValidConstructor((Class<?>) aClass);
            }

            //For JUnit4
            int modifiers = ((Class<?>) aClass).getModifiers();

            if (!(Modifier.isPublic(modifiers) && !Modifier.isAbstract(modifiers))) {
                return false;
            }
            
            if (hasAnnotation(aClass, RunWith.class)) {
                return true;
            }

            if (hasSuiteMethod(aClass)) {
                return true;
            }
            
            if (hasAnnotatedMethod(aClass)) {
                return true;
            }
            
            return false;
        }
        
        private boolean hasSuiteMethod(Class<?> testClass) {
            //FIXME Use metadata.
            try {
                testClass.getMethod("suite");
            } catch (NoSuchMethodException e) {
                return false;
            }
            return true;
        }
        
        private static boolean hasAnnotation(AnnotatedElement meta, Class<? extends Annotation> annon) {
            return meta.isAnnotationPresent(annon);
        }
        
        private static boolean hasAnnotatedMethod(Class<?> aClass) {
            try {
                //FIXME Scan superclasses's methods.
                for (Method method: aClass.getMethods()) {
                    if (hasAnnotation(method, Test.class)) {
                        return true;
                    }
                }
            } catch (Exception ignore) {
            } catch (LinkageError ignore) {
            }
            return false;
        }

        @SuppressWarnings("unchecked")
        private boolean hasValidConstructor(java.lang.Class<?> aClass) {
            // The cast below is not necessary with the Java 5 compiler, but necessary with the Java 6 compiler,
            // where the return type of Class.getDeclaredConstructors() was changed
            // from Constructor<T>[] to Constructor<?>[]
            Constructor<? extends TestCase>[] constructors
                    = (Constructor<? extends TestCase>[]) aClass.getConstructors();
            for (Constructor<? extends TestCase> constructor : constructors) {
                if (Modifier.isPublic(constructor.getModifiers())) {
                    java.lang.Class<?>[] parameterTypes = constructor.getParameterTypes();
                    if (parameterTypes.length == 0 ||
                            (parameterTypes.length == 1 && parameterTypes[0] == String.class)) {
                        return true;
                    }
                }
            }
            Log.i(LOG_TAG, String.format(
                    "TestCase class %s is missing a public constructor with no parameters " +
                    "or a single String parameter - skipping",
                    aClass.getName()));
            return false;
        }
    }
}
