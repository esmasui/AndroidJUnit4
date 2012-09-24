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
package com.uphyca.testing.support.v4;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class DelegateFactory {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    public @interface DeclaredIn {
        String value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Parameter {
        boolean isStatic() default false;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Delegate {
    }

    private static final class InvocationHandlerImpl implements InvocationHandler {
        final Object mDelegate;
        final Class<?> mClass;
        final Map<Method, Method> mMethodCache;
        final Map<Method, Field> mFieldCache;
        final Map<Method, Object> mNegativeCache;
        private final static Object UNRESOLVED = new Object();

        InvocationHandlerImpl(Class<?> receiver,
                              Class<?> delegateClass,
                              Object delegateInstance) {
            mDelegate = delegateInstance;
            mClass = delegateClass;
            mMethodCache = new ConcurrentHashMap<Method, Method>();
            mFieldCache = new ConcurrentHashMap<Method, Field>();
            mNegativeCache = new ConcurrentHashMap<Method, Object>();
        }

        public final Object invoke(Object target,
                                   Method method,
                                   Object[] args) throws Throwable {

            if (isParameter(method)) {
                return obtainField(method,
                                   args);
            }

            Method delegateMethod = getMethodFromCache(method);

            if (!hasDelegateMethod(delegateMethod)) {
                return unresolved(method.getReturnType());
            }

            Object receiver = null;
            boolean staticMethod = isMethodStatic(delegateMethod);

            if (!staticMethod) {
                receiver = mDelegate;
            }

            boolean invoke = canInvoke(staticMethod,
                                       receiver);

            if (!invoke) {
                return null;
            }

            Object res = delegateMethod.invoke(receiver,
                                               args);

            if (method.getReturnType().isAnnotationPresent(Delegate.class)) {
                return DelegateFactory.create(method.getReturnType(),
                                              res);
            }

            return res;
        }

        private Object obtainField(Method method,
                                   Object[] args) throws IllegalArgumentException, IllegalAccessException {
            Field field = getFieldFromCache(method);

            if (field == null)
                return null;

            if (args == null || args.length == 0) {

                Object f = field.get(mDelegate);

                if (method.getReturnType().isAnnotationPresent(Delegate.class)) {

                    return DelegateFactory.create(method.getReturnType(),
                                                  f);
                }

                return f;
            }

            field.set(mDelegate,
                      args[0]);
            return null;
        }

        private Object unresolved(Class<?> returnType) {
            if (!returnType.isPrimitive())
                return null;
            if (returnType.equals(Boolean.TYPE))
                return false;
            if (returnType.equals(Byte.TYPE))
                return (byte) 0;
            if (returnType.equals(Short.TYPE))
                return (short) 0;
            if (returnType.equals(Integer.TYPE))
                return 0;
            if (returnType.equals(Long.TYPE))
                return 0L;
            if (returnType.equals(Float.TYPE))
                return 0F;
            if (returnType.equals(Double.TYPE))
                return 0D;
            return null;
        }

        private Method getMethodFromCache(Method method) throws SecurityException {
            Method delegate = mMethodCache.get(method);

            if (delegate != null)
                return delegate;

            if (mNegativeCache.containsKey(method)) {
                return null;
            }

            delegate = getDelegateMethod(method,
                                         mClass);

            if (delegate == null) {
                mNegativeCache.put(method,
                                   UNRESOLVED);

                return null;
            }

            makeAccessible(delegate);
            mMethodCache.put(method,
                             delegate);
            return delegate;
        }

        private Field getFieldFromCache(Method method) throws SecurityException {
            Field field = mFieldCache.get(method);

            if (field != null)
                return field;

            if (mNegativeCache.containsKey(method)) {
                return null;
            }

            field = getField(method,
                             mClass);

            if (field == null) {
                mNegativeCache.put(method,
                                   UNRESOLVED);

                return null;
            }

            makeAccessible(field);
            mFieldCache.put(method,
                            field);
            return field;
        }

    }

    static final boolean canInvoke(boolean staticMethod,
                                   Object receiver) {
        if (staticMethod) {
            return true;
        }

        boolean b = receiver != null;
        return b;
    }

    static final <T> T create(Class<T> type,
                              Class<?> delegateClass,
                              Object delegateInstance) {
        if (type == null) {
            throw new IllegalArgumentException("type must not be null");
        }

        if (delegateClass == null) {
            throw new IllegalArgumentException("delegateClass must not be null");
        }

        InvocationHandler handler = new InvocationHandlerImpl(type, delegateClass, delegateInstance);
        ClassLoader classLoader = type.getClassLoader();
        Class<?>[] types = new Class[] { type };
        Object proxy = Proxy.newProxyInstance(classLoader,
                                              types,
                                              handler);
        T t = type.cast(proxy);
        return t;
    }

    public static final <T> T create(Class<T> type,
                                     Object delegate) {
        if (delegate == null) {
            throw new IllegalArgumentException("delegate must not be null");
        }

        T t = create(type,
                     delegate.getClass(),
                     delegate);
        return t;
    }

    public static final <T> T create(Class<T> type,
                                     String delegateClassName,
                                     Object delegate) throws ClassNotFoundException {
        return create(type,
                      delegateClassName,
                      type.getClassLoader(),
                      delegate);
    }

    public static final <T> T create(Class<T> type,
                                     String delegateClassName,
                                     ClassLoader classLoader,
                                     Object delegate) throws ClassNotFoundException {
        if (delegateClassName == null) {
            throw new IllegalArgumentException("delegateClassName must not be null");
        }
        Class<?> delegateClass = classLoader.loadClass(delegateClassName);
        T t = create(type,
                     delegateClass,
                     delegate);
        return t;
    }

    static final Method getDeclaredMethod(Class<?> type,
                                          String name,
                                          Class<?>[] params) {
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {

            Method[] methods = c.getDeclaredMethods();
            Method m = getMostMatchMethod(name,
                                          params,
                                          methods);
            boolean b = m != null;

            if (b) {
                return m;
            }
        }

        return null;
    }

    static final Method getDelegateMethod(Method method,
                                          Class<?> clazz) throws SecurityException {
        String name = method.getName();
        Class<?>[] params = method.getParameterTypes();
        Method delegateMethod;

        try {
            delegateMethod = clazz.getMethod(name,
                                             params);
            return delegateMethod;
        } catch (NoSuchMethodException e) {
        }

        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0, num = parameterAnnotations.length; i < num; ++i) {
            Annotation[] annotations = parameterAnnotations[i];
            if (annotations.length <= 0)
                continue;
            for (int j = 0, jnum = annotations.length; j < jnum; ++j) {
                Annotation annon = annotations[j];
                Class<?> annonType = annon.annotationType();
                if (!DeclaredIn.class.isAssignableFrom(annonType))
                //if (!DeclaredIn.class.equals(annonType))
                    continue;

                DeclaredIn t = (DeclaredIn) annon;
                try {
                    Class<?> type = DelegateFactory.class.getClassLoader().loadClass(t.value());
                    params[i] = type;
                    continue;
                } catch (ClassNotFoundException e) {
                    params[i] = Object.class;
                    //throw new RuntimeException(e);
                }
            }
        }

        delegateMethod = getDeclaredMethod(clazz,
                                           name,
                                           params);
        return delegateMethod;
    }

    static final Field getField(Method method,
                                Class<?> clazz) throws SecurityException {

        String name = method.getName();
        String fieldName;
        if (name.startsWith("set")) {
            fieldName = name.substring(3);
        } else if (name.startsWith("is")) {
            fieldName = name.substring(2);
        } else if (name.startsWith("get")) {
            fieldName = name.substring(3);
        } else {
            throw new IllegalArgumentException();
        }

        Field field = null;

        if (method.getAnnotation(Parameter.class).isStatic()) {
            try {
                field = clazz.getField(fieldName);
                return field;
            } catch (NoSuchFieldException e) {
            }
            return null;
        } else {
            String canonicalName = fieldName.substring(0,
                                                       1).toLowerCase() + fieldName.substring(1);
            for (Class<?> cls = clazz; cls != null; cls = cls.getSuperclass()) {
                try {
                    field = clazz.getDeclaredField(canonicalName);
                } catch (NoSuchFieldException e) {
                }
                if (field != null) {
                    return field;
                }
            }

        }

        return null;
    }

    static final Method getMostMatchMethod(String name,
                                           Class<?>[] params,
                                           Method[] methods) {
        for (Method m : methods) {
            boolean b = isSignatureMatches(m,
                                           name,
                                           params);

            if (b) {
                return m;
            }
        }

        return null;
    }

    static final boolean hasDelegateMethod(Method m) {
        return m != null;
    }

    static final boolean isParameter(Method m) {
        return m.isAnnotationPresent(Parameter.class);
    }

    static final boolean isMethodStatic(Method m) {
        int modifiers = m.getModifiers();
        boolean b = Modifier.isStatic(modifiers);
        return b;
    }

    static final boolean isSignatureMatches(Method m,
                                            String name,
                                            Class<?>[] params) {
        boolean b;
        String n = m.getName();
        b = n.equals(name);

        if (!b) {
            return false;
        }

        Class<?>[] delegateParams = m.getParameterTypes();

        b = isParameterMatches(params,
                               delegateParams);
        return b;
    }

    static final boolean isParameterMatches(Class<?>[] params,
                                            Class<?>[] delegateParams) {
        //        if (Arrays.equals(params, delegateParams))
        //            return true;
        if (params.length != delegateParams.length) {
            return false;
        }

        for (int i = 0, num = params.length; i < num; ++i) {
            if (params[i].isPrimitive()) {
                if (!params[i].equals(delegateParams[i])) {
                    return false;
                }
            } else if (!delegateParams[i].isAssignableFrom(params[i])) {
                return false;
            }
        }
        return true;
    }

    static final void makeAccessible(Method m) {
        if (m == null) {
            return;
        }

        if (m.isAccessible()) {
            return;
        }

        m.setAccessible(true);
    }

    static final void makeAccessible(Field f) {
        if (f == null) {
            return;
        }

        if (f.isAccessible()) {
            return;
        }

        f.setAccessible(true);
    }

}