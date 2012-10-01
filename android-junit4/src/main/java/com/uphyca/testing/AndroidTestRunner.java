package com.uphyca.testing;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class AndroidTestRunner extends BlockJUnit4ClassRunner {

    public AndroidTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }
}
