package com.uphyca.testing;

import java.io.File;
import java.lang.reflect.Method;

import org.junit.runners.model.InitializationError;

import com.uphyca.testing.shadows.CustomShadowDialogFragment;
import com.uphyca.testing.shadows.CustomShadowFragment;
import com.uphyca.testing.shadows.CustomShadowTimePicker;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricConfig;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.bytecode.ClassHandler;
import com.xtremelabs.robolectric.bytecode.RobolectricClassLoader;
import com.xtremelabs.robolectric.util.DatabaseConfig.DatabaseMap;

public class AndroidTestRunner extends RobolectricTestRunner {

    public AndroidTestRunner(Class<?> arg0, ClassHandler arg1, RobolectricClassLoader arg2, RobolectricConfig arg3, DatabaseMap arg4) throws InitializationError {
        super(arg0, arg1, arg2, arg3, arg4);
    }

    public AndroidTestRunner(Class<?> testClass, ClassHandler classHandler, RobolectricClassLoader classLoader, RobolectricConfig robolectricConfig) throws InitializationError {
        super(testClass, classHandler, classLoader, robolectricConfig);
    }

    public AndroidTestRunner(Class<?> testClass, ClassHandler classHandler, RobolectricConfig robolectricConfig) throws InitializationError {
        super(testClass, classHandler, robolectricConfig);
    }

    public AndroidTestRunner(Class<?> testClass, File androidManifestPath, File resourceDirectory) throws InitializationError {
        super(testClass, androidManifestPath, resourceDirectory);
    }

    public AndroidTestRunner(Class<?> testClass, File androidProjectRoot) throws InitializationError {
        super(testClass, androidProjectRoot);
    }

    public AndroidTestRunner(Class<?> testClass, RobolectricConfig robolectricConfig, DatabaseMap databaseMap) throws InitializationError {
        super(testClass, robolectricConfig, databaseMap);
    }

    public AndroidTestRunner(Class<?> testClass, RobolectricConfig robolectricConfig) throws InitializationError {
        super(testClass, robolectricConfig);
    }

    public AndroidTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    public void beforeTest(Method method) {
        Robolectric.bindShadowClass(CustomShadowFragment.class);
        Robolectric.bindShadowClass(CustomShadowDialogFragment.class);
        Robolectric.bindShadowClass(CustomShadowTimePicker.class);
    }
}
