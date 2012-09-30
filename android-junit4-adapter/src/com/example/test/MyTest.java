package com.example.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MyTest {

    @Test
    public void assertPreconditions() {
        assertTrue(true);
    }

    @Test
    public void shouldHaveTheMessage() {
        assertThat(getMessage(), is("Hello, world."));
    }

    /**
     * 実際はテスト対象のメソッドをテストするが簡略化のため自身のメソッドを呼び出す
     */
    private static String getMessage() {
        return "Hello, world.";
    }

}
