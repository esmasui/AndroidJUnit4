package com.uphyca.testing.junit.internal.builders;

import org.junit.internal.builders.AnnotatedBuilder;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

import com.uphyca.testing.junit.runners.AndroidSuite;

public class AndroidAnnotatedBuilder extends AnnotatedBuilder {

    private static final String CONSTRUCTOR_ERROR_FORMAT = "Custom runner class %s should have a public constructor with signature %s(Class testClass)";

    private RunnerBuilder fSuiteBuilder;

    public AndroidAnnotatedBuilder(RunnerBuilder suiteBuilder) {
        super(suiteBuilder);
        fSuiteBuilder = suiteBuilder;
    }

    @Override
    public Runner runnerForClass(Class<?> testClass) throws Exception {
        RunWith annotation = testClass.getAnnotation(RunWith.class);
        if (annotation != null) {
            //For Android
            Class<? extends Runner> runnerClass = useAndroidRunner(annotation.value());
            return buildRunner(runnerClass,
                               testClass);
        }
        return null;
    }

    public Runner buildRunner(Class<? extends Runner> runnerClass,
                              Class<?> testClass) throws Exception {
        try {
            return runnerClass.getConstructor(Class.class).newInstance(new Object[] { testClass });
        } catch (NoSuchMethodException e) {
            try {
                return runnerClass.getConstructor(Class.class,
                                                  RunnerBuilder.class).newInstance(new Object[] { testClass, fSuiteBuilder });
            } catch (NoSuchMethodException e2) {
                String simpleName = runnerClass.getSimpleName();
                throw new InitializationError(String.format(CONSTRUCTOR_ERROR_FORMAT,
                                                            simpleName,
                                                            simpleName));
            }
        }
    }

    //For Android
    private Class<? extends Runner> useAndroidRunner(Class<? extends Runner> runnerClass) {
        if (runnerClass.equals(Suite.class)) {
            return AndroidSuite.class;
        }
        return runnerClass;
    }
}
