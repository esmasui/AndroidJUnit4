package com.uphyca.testing.junit3.support.v4.test;

import junit.framework.TestCase;

import com.uphyca.testing.support.v4.DelegateFactory;
import com.uphyca.testing.support.v4.DelegateFactory.DeclaredIn;

public class DelegateFactoryTest extends TestCase {

    public static final class TargetClass {
        public String sayHello(String name) {
            return "hello, " + name;
        }
    }

    public static interface DelegateInterface {
        String sayHello(@DeclaredIn("java.lang.String") Object name);
    }

    public static interface InvalidDelegateInterface {
        String sayHello(Object name);
    }

    public void testAnnotatedParameters() throws Exception {
        DelegateInterface actual = DelegateFactory.create(DelegateInterface.class, new TargetClass());
        assertEquals("hello, delegate", actual.sayHello("delegate"));
    }

    public void testNonAnnotatedParameters() throws Exception {
        InvalidDelegateInterface actual = DelegateFactory.create(InvalidDelegateInterface.class, new TargetClass());
        assertNull(actual.sayHello("delegate"));
    }
}
