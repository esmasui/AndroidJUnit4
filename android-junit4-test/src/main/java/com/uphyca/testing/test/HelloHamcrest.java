package com.uphyca.testing.test;

import static org.junit.Assert.assertThat;
import junit.framework.JUnit4TestAdapter;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class HelloHamcrest {

    /**
     * For Eclipse with ADT
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(HelloHamcrest.class);
    }

    public String sayHello() {
        return "Hello, world.";
    }

    @Test
    public void helloJUnit4() {
        assertThat(sayHello(), CoreMatchers.is("Hello, world."));
    }
}
