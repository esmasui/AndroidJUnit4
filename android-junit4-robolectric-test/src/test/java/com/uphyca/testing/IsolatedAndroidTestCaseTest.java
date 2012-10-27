package com.uphyca.testing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import junit.framework.JUnit4TestAdapter;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

@RunWith(AndroidTestRunner.class)
@Ignore("temporary disable that fails when run on command line.")
public class IsolatedAndroidTestCaseTest extends IsolatedAndroidTestCase {

    public static final junit.framework.Test suite() {
        return new JUnit4TestAdapter(IsolatedAndroidTestCaseTest.class);
    }

    @Test
    public void assertPreconditions() {
        assertNotNull(getMockContext());
    }

    private void invokeDatabaseOperation() {
        Context context = getMockContext();
        String database = "test.db";
        CursorFactory factory = null;
        int version = 1;
        SQLiteOpenHelper helper = new SQLiteOpenHelper(context,
                                                       database,
                                                       factory,
                                                       version) {

            @Override
            public void onUpgrade(SQLiteDatabase db,
                                  int oldVersion,
                                  int newVersion) {
                onCreate(db);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL("create table test (_id integer primary key autoincrement not null, name varchar);");
            }
        };
        SQLiteDatabase db = helper.getWritableDatabase();

        assertThat(db.query("test", null, null, null, null, null, null)
                     .getCount(), is(0));

        ContentValues values = new ContentValues();
        values.put("name", "foo");
        db.insert("test", null, values);

        assertThat(db.query("test", null, null, null, null, null, null)
                     .getCount(), is(1));
    }

    /**
     * If context is not isolated when thatDatabaseShouldBeIsolated1 or thatDatabaseShouldBeIsolated2 got failed.
     */
    @Test
    public void thatDatabaseShouldBeIsolated1() {
        invokeDatabaseOperation();
    }

    /**
     * If context is not isolated when thatDatabaseShouldBeIsolated1 or thatDatabaseShouldBeIsolated2 got failed.
     */
    @Test
    public void thatDatabaseShouldBeIsolated2() {
        invokeDatabaseOperation();
    }
}
