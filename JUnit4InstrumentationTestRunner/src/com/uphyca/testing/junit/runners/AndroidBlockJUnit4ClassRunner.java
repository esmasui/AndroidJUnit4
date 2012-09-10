package com.uphyca.testing.junit.runners;

import org.junit.internal.runners.statements.InvokeMethod;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import android.app.Instrumentation;

import com.uphyca.testing.InstrumentationSupport;
import com.uphyca.testing.junit.internal.runners.statements.AndroidInvokeMethod;

public class AndroidBlockJUnit4ClassRunner extends BlockJUnit4ClassRunner {

    public AndroidBlockJUnit4ClassRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    /**
     * Returns a {@link Statement} that invokes {@code method} on {@code test}
     */
    @Override
    protected Statement methodInvoker(FrameworkMethod method,
                                      Object test) {
        InvokeMethod invokeMethod = new InvokeMethod(method, test);
        Instrumentation instrumentation = null;
        if (test instanceof InstrumentationSupport) {
            instrumentation = ((InstrumentationSupport) test).getInstrumentation();
        }
        AndroidInvokeMethod androidInvokeMethod = new AndroidInvokeMethod(method, invokeMethod, instrumentation);
        return androidInvokeMethod;
    }
}
