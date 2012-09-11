package com.uphyca.example.mockito;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import junit.framework.JUnit4TestAdapter;

import org.junit.Test;

public class HelloMockito {

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(HelloMockito.class);
    }

    @Test
    public void verifyInteractions() {
        // mock creation:
        List<String> mockedList = mock(List.class);

        // using mock object - doesn't throw any "unexpected interaction"
        // exception
        mockedList.add("one");
        mockedList.clear();

        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test
    public void stubMethodCalls() {
        // You can mock concrete classes, not only interfaces
        LinkedList<String> mockedList = mock(LinkedList.class);

        // stubbing - before excution
        when(mockedList.get(0)).thenReturn("first");

        // following prints "first"
        assertThat(mockedList.get(0), equalTo("first"));

        // following prints "null" because get(999) was not stubbed
        assertNull(mockedList.get(999));
    }
}
