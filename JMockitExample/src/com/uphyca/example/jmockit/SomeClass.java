package com.uphyca.example.jmockit;

public final class SomeClass
{
	public String doSomething(int i)
	{
		SomeOtherClass other = new SomeOtherClass("data");
		return other.performSomeOperation(i);
	}
}
