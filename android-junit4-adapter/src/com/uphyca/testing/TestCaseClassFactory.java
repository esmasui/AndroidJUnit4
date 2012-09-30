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
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import junit.framework.TestCase;
import junit.framework.TestResult;

import com.google.dexmaker.Code;
import com.google.dexmaker.DexMaker;
import com.google.dexmaker.Local;
import com.google.dexmaker.MethodId;
import com.google.dexmaker.TypeId;

abstract class TestCaseClassFactory {

    private static String toTypeString(Class<?> klass) {
        return String.format("L%s;", klass.getName()
                                          .replace('.', '/'));
    }

    static Class<TestCase> createTestCaseClass(Class<?> klass,
                                               File outputDir) throws Exception {
        DexMaker dexMaker = new DexMaker();
        TypeId<TestCase> myTestCase = TypeId.get(toTypeString(klass));
        TypeId<TestCase> testCase = TypeId.get(toTypeString(TestCase.class));
        dexMaker.declare(myTestCase, klass.getName() + ".generated", Modifier.PUBLIC, testCase);
        generateConstructor(dexMaker, myTestCase);
        generateRunMethod(dexMaker, myTestCase);
        generateAllTestMethods(dexMaker, myTestCase, klass);
        ClassLoader parent = klass.getClassLoader()
                                  .getParent();
        ClassLoader loader = dexMaker.generateAndLoad(parent, outputDir);
        @SuppressWarnings("unchecked")
        Class<TestCase> myTestCaseClass = (Class<TestCase>) loader.loadClass(klass.getName());
        return myTestCaseClass;
    }

    private static void generateAllTestMethods(DexMaker dexMaker,
                                               TypeId<TestCase> declaringType,
                                               Class<?> klass) {
        for (Method method : klass.getMethods()) {
            if (method.isAnnotationPresent(org.junit.Test.class)) {
                generateSingleNoOpMethod(dexMaker, declaringType, method);
            }
        }
    }

    private static void generateRunMethod(DexMaker dexMaker,
                                          TypeId<TestCase> declaringType) {
        try {
            Method method = TestCase.class.getMethod("run", TestResult.class);
            generateSingleNoOpMethod(dexMaker, declaringType, method);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
    }

    private static void generateConstructor(DexMaker dexMaker,
                                            TypeId<TestCase> declaringType) {
        MethodId<?, Void> constructor = declaringType.getConstructor();
        TypeId<TestCase> testCase = TypeId.get(toTypeString(TestCase.class));
        MethodId<TestCase, Void> superConstructor = testCase.getConstructor();
        Code constructorCode = dexMaker.declare(constructor, Modifier.PUBLIC);
        Local<TestCase> thisRef = constructorCode.getThis(declaringType);
        constructorCode.invokeDirect(superConstructor, null, thisRef);
        constructorCode.returnVoid();
    }

    private static void generateSingleNoOpMethod(DexMaker dexMaker,
                                                 TypeId<TestCase> declaringType,
                                                 Method method) {
        Class<?>[] argClasses = method.getParameterTypes();
        TypeId<?>[] argTypes = new TypeId<?>[argClasses.length];
        for (int i = 0; i < argTypes.length; ++i) {
            argTypes[i] = TypeId.get(argClasses[i]);
        }

        MethodId<?, ?> methodId = declaringType.getMethod(TypeId.VOID, method.getName(), argTypes);
        Code code = dexMaker.declare(methodId, Modifier.PUBLIC);
        code.returnVoid();
    }
}
