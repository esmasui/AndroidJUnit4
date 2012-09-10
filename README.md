AndroidJUnit4
=============

AndroidJUnit4 is a framework to running JUnit4 tests on Android devices.


Usage:

1. Use com.uphyca.testing.JUnit4InstrumentationTestRunner for instrumentation

    <instrumentation
        android:name="com.uphyca.testing.JUnit4InstrumentationTestRunner"
        android:targetPackage="your.test.package" />

2. Extends base classes for Android dependent tests.

    Base classses located in com.uphyca.testing package as same name of original android.test package's classes.
    For example, JUnit4 version of android.test.ActivityUnitTestCase is com.uphyca.testing.ActivityUnitTestCase.


