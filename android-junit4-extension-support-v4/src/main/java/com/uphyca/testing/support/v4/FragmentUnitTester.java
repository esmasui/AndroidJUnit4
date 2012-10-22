package com.uphyca.testing.support.v4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.uphyca.testing.Infrastructure;
import com.uphyca.testing.junit3.support.v4.FragmentUnitTestCase;

class FragmentUnitTester<T extends Fragment> extends FragmentUnitTestCase<T> {

    public FragmentUnitTester(Object owner,
                              Class<T> fragmentClass) {
        super(fragmentClass);
        Infrastructure.setInstrumentationToInstrumentationTest(this);
        Infrastructure.setPerformanceWriterIfPerformanceCollectorTestCase(owner);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.uphyca.testing.junit3.support.v4.FragmentUnitTestCase#setUp()
     */
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.uphyca.testing.junit3.support.v4.FragmentUnitTestCase#tearDown()
     */
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.uphyca.testing.junit3.support.v4.FragmentUnitTestCase#startFragment
     * (android.os.Bundle, android.os.Bundle, java.lang.Object)
     */
    @Override
    public T startFragment(Bundle arguments,
                           Bundle savedInstanceState,
                           Object lastNonConfigurationInstance) {
        return super.startFragment(arguments, savedInstanceState, lastNonConfigurationInstance);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.uphyca.testing.junit3.support.v4.FragmentUnitTestCase#getFragmentManager
     * ()
     */
    public FragmentManager getFragmentManager() {
        return super.getFragmentManager();
    }
    
    /* (non-Javadoc)
     * @see com.uphyca.testing.junit3.support.v4.FragmentUnitTestCase#getActivity()
     */
    public FragmentActivity getActivity(){
        return super.getActivity();
    }
}
