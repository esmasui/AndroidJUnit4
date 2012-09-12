package com.uphyca.example.jmockit;

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

import org.junit.*;
import mockit.*;

public final class SomeClassTest
{
    public static final junit.framework.Test sutie(){
        return new JUnit4TestAdapter(SomeClassTest.class);
    }
    
	@Tested SomeClass sut;
	@NonStrict SomeOtherClass mock;
	
	@Test
	public void testSomething()
	{
		new Expectations() {{ mock.performSomeOperation(anyInt); result = "mocked"; }};
		
		assertEquals("mocked", sut.doSomething(123));
	}
}
