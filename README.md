AndroidJUnit4
=============

AndroidJUnit4 is a framework to running JUnit4 tests on Android devices with Eclipse/ADT or command line tools.


Supported API versions;

    API level 3 (Cupcake) or later.


Build instruction;

    1. Install Maven (http://maven.apache.org/)
    2. Clone repository
    
        $ git clone https://github.com/esmasui/AndroidJUnit4.git AndroidJUnit4-readonly
    
    3. Build libraries
        
        $ cd AndroidJUnit4-readonly
        $ mvn eclipse:eclipse install
    
    4. Import AndroidJunit4-readonly/android-junit4 as a Android library project or jar file to your Android test project.


Continuous integration;
    http://jenkins.android-tec.org/job/JUnit4InstrumentationTestRunnerMaven/


Usage;

    1. Set com.uphyca.testing.JUnit4InstrumentationTestRunner into instrumentation in your AndroidManifest.xml

        <instrumentation
            android:name="com.uphyca.testing.JUnit4InstrumentationTestRunner"
            android:targetPackage="your.test.package" />

    2. Write your test classes that extends base test classes for Android dependent tests.

        The base test classses located in com.uphyca.testing package as same name of original android.test package's classes.
        For example; JUnit4 version of android.test.ActivityUnitTestCase is com.uphyca.testing.ActivityUnitTestCase.
    
    Supported test classes are
        AndroidTestCase
        InstrumentationTestCase
        ActivityInstrumentationTestCase<T extends Activity>
        ActivityInstrumentationTestCase2<T extends Activity>
        ActivityTestCase, ActivityUnitTestCase<T extends Activity>
        ApplicationTestCase<T extends Application>
        LoaderTestCase
        ProviderTestCase<T extends ContentProvider>
        ProviderTestCase2<T extends ContentProvider>
        ServiceTestCase<T extends Service>
        SingleLaunchActivityTestCase<T extends Activity>
        SyncBaseInstrumentation


Example;


    package com.uphyca.testing.test;
	
	import static org.hamcrest.CoreMatchers.equalTo;
	import static org.junit.Assert.assertNotNull;
	import static org.junit.Assert.assertThat;
	
	import org.junit.Before;
	import org.junit.Test;
	
	import android.app.Activity;
	import android.content.Intent;
	import android.os.Bundle;
	import android.widget.TextView;
	
	import com.uphyca.testing.ActivityUnitTestCase;
	import com.uphyca.testing.AndroidJUnit4TestAdapter;
	import com.uphyca.testing.test.HelloActivity.MyActivity;
	
	public class HelloActivity extends ActivityUnitTestCase<MyActivity> {
	
	    /**
	     * For Eclipse with ADT
	     */
	    public static junit.framework.Test suite() {
	        // Should use AndroidJUnit4TestAdapter for to running AndroidDependent
	        // TestCases.
	        return new AndroidJUnit4TestAdapter(HelloActivity.class);
	    }
	
	    public static class MyActivity extends Activity {
	        @Override
	        protected void onCreate(Bundle savedInstanceState) {
	            super.onCreate(savedInstanceState);
	            TextView view = new TextView(this);
	            view.setText("Hello, activity.");
	            view.setId(android.R.id.text1);
	            setContentView(view);
	        }
	    }
	
	    private Intent startIntent;
	
	    public HelloActivity() {
	        super(MyActivity.class);
	    }
	
	    @Override
	    @Before
	    public void setUp() throws Exception {
	        super.setUp();
	        startIntent = new Intent(Intent.ACTION_MAIN);
	    }
	
	    @Test
	    public void assertPreconditions() {
	        startActivity(startIntent, null, null);
	        assertNotNull(getActivity());
	    }
	
	    @Test
	    public void sayHello() {
	        startActivity(startIntent, null, null);
	        assertThat(((TextView) getActivity().findViewById(android.R.id.text1)).getText()
                                                                          .toString(), equalTo("Hello, activity."));
	    }
	
	}


License;


    Copyright (C) 2012 uPhyca Inc.
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
          http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.



