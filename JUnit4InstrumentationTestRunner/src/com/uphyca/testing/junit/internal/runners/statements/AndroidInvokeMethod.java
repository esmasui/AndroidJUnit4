package com.uphyca.testing.junit.internal.runners.statements;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.internal.runners.statements.InvokeMethod;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import android.app.Instrumentation;
import android.os.Bundle;
import android.test.FlakyTest;
import android.test.RepetitiveTest;
import android.test.UiThreadTest;

/**
 * FIXME supports Android test's annotations such as FleakyTest.
 */
public class AndroidInvokeMethod extends Statement {

    private FrameworkMethod fFrameworkMethod;
    private InvokeMethod fMethod;
    private Instrumentation fInstrumentation;

    public AndroidInvokeMethod(FrameworkMethod frameworkMethod,
                                InvokeMethod method,
                                Instrumentation instrumentation) {
        fFrameworkMethod = frameworkMethod;
        fMethod = method;
        fInstrumentation = instrumentation;
    }

    @Override
    public void evaluate() throws Throwable {

        final Method method = fFrameworkMethod.getMethod();
        
        //Following code taken from android.test.InstrumentationTestCase.
        
        int runCount = 1;
        boolean isRepetitive = false;
        if (method.isAnnotationPresent(FlakyTest.class)) {
            runCount = method.getAnnotation(FlakyTest.class).tolerance();
        } else if (method.isAnnotationPresent(RepetitiveTest.class)) {
            runCount = method.getAnnotation(RepetitiveTest.class).numIterations();
            isRepetitive = true;
        }

        if (fInstrumentation != null && method.isAnnotationPresent(UiThreadTest.class)) {
            final int tolerance = runCount;
            final boolean repetitive = isRepetitive;
            final Method testMethod = method;
            final Throwable[] exceptions = new Throwable[1];
            fInstrumentation.runOnMainSync(new Runnable() {
                public void run() {
                    try {
                        runMethod(testMethod, tolerance, repetitive);
                    } catch (Throwable throwable) {
                        exceptions[0] = throwable;
                    }
                }
            });
            if (exceptions[0] != null) {
                throw exceptions[0];
            }
        } else {
            runMethod(method, runCount, isRepetitive);
        }
    }
    
    //Taken from android.test.InstrumentationTestCase
    private void runMethod(Method runMethod, int tolerance, boolean isRepetitive) throws Throwable {
        Throwable exception = null;

        int runCount = 0;
        do {
            try {
                fMethod.evaluate();
                exception = null;
            } catch (InvocationTargetException e) {
                e.fillInStackTrace();
                exception = e.getTargetException();
            } catch (IllegalAccessException e) {
                e.fillInStackTrace();
                exception = e;
            } catch (Throwable e) {
                e.fillInStackTrace();
                exception = e;
            } finally {
                runCount++;
                // Report current iteration number, if test is repetitive
                if (isRepetitive) {
                    Bundle iterations = new Bundle();
                    iterations.putInt("currentiterations", runCount);
                    fInstrumentation.sendStatus(2, iterations);
                }
            }
        } while ((runCount < tolerance) && (isRepetitive || exception != null));

        if (exception != null) {
            throw exception;
        }
    }
}
