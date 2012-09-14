package com.uphyca.testing;

import java.lang.reflect.InvocationTargetException;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import android.app.Instrumentation;
import android.os.Bundle;
import android.test.FlakyTest;
import android.test.RepetitiveTest;
import android.test.UiThreadTest;

class AndroidAnnotatedMethodRule implements TestRule {

    private Instrumentation fInstrumentation;

    public AndroidAnnotatedMethodRule() {
    }

    public AndroidAnnotatedMethodRule(Instrumentation instrumentation) {
        fInstrumentation = instrumentation;
    }

    @Override
    public Statement apply(final Statement statement,
                           final Description description) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                //String methodName = description.getMethodName();
                //methodName.
                //FIXME How to get annotations via description ?
                //Method method = description.getTestClass().getMethod(methodName);

                //Following code taken from android.test.InstrumentationTestCase.

                int runCount = 1;
                boolean isRepetitive = false;
                if (description.getAnnotation(FlakyTest.class) != null) {
                    runCount = description.getAnnotation(FlakyTest.class).tolerance();
                } else if (description.getAnnotation(RepetitiveTest.class) != null) {
                    runCount = description.getAnnotation(RepetitiveTest.class).numIterations();
                    isRepetitive = true;
                }

                if (fInstrumentation != null && description.getAnnotation(UiThreadTest.class) != null) {
                    final int tolerance = runCount;
                    final boolean repetitive = isRepetitive;
                    final Throwable[] exceptions = new Throwable[1];
                    fInstrumentation.runOnMainSync(new Runnable() {
                        public void run() {
                            try {
                                runMethod(statement,
                                          tolerance,
                                          repetitive);
                            } catch (Throwable throwable) {
                                exceptions[0] = throwable;
                            }
                        }
                    });
                    if (exceptions[0] != null) {
                        throw exceptions[0];
                    }
                } else {
                    runMethod(statement,
                              runCount,
                              isRepetitive);
                }
            }
        };
    }

    //Taken from android.test.InstrumentationTestCase
    private void runMethod(Statement statement,
                           int tolerance,
                           boolean isRepetitive) throws Throwable {
        Throwable exception = null;

        int runCount = 0;
        do {
            try {
                statement.evaluate();
                exception = null;
            } catch (InvocationTargetException e) {
                e.fillInStackTrace();
                exception = e.getTargetException();
            } catch (IllegalAccessException e) {
                e.fillInStackTrace();
                exception = e;
            } catch (Throwable e) {
                exception = e;
            } finally {
                runCount++;
                // Report current iteration number, if test is repetitive
                if (isRepetitive) {
                    Bundle iterations = new Bundle();
                    iterations.putInt("currentiterations",
                                      runCount);
                    fInstrumentation.sendStatus(2,
                                                iterations);
                }
            }
        } while ((runCount < tolerance) && (isRepetitive || exception != null));

        if (exception != null) {
            throw exception;
        }
    }
}
