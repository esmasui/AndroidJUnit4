package com.uphyca.testing.junit4.extnsion;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.uphyca.testing.Infrastructure;

class SQLiteOpenHelperTester<T extends SQLiteOpenHelper> extends com.uphyca.testing.junit3.extension.SQLiteOpenHelperTestCase<T> {

    public SQLiteOpenHelperTester(Object owner,
                                  Class<T> openHelperClass) {
        super(openHelperClass);
        Infrastructure.setContextToAndroidTestCase(this);
        Infrastructure.setPerformanceWriterIfPerformanceCollectorTestCase(owner);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.uphyca.testing.junit3.SQLiteOpenHelperTestCase#setUp()
     */
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.uphyca.testing.junit3.SQLiteOpenHelperTestCase#tearDown()
     */
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.test.AndroidTestCase#scrubClass(java.lang.Class)
     */
    @Override
    public void scrubClass(Class<?> testCaseClass) throws IllegalAccessException {
        super.scrubClass(testCaseClass);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.uphyca.testing.junit3.SQLiteOpenHelperTestCase#createSQLiteOpenHelper
     * (android.content.Context)
     */
    @Override
    public T createSQLiteOpenHelper(Context context) throws Exception {
        return super.createSQLiteOpenHelper(context);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.uphyca.testing.junit3.SQLiteOpenHelperTestCase#getMockContext()
     */
    @Override
    public Context getMockContext() {
        return super.getMockContext();
    }
}
