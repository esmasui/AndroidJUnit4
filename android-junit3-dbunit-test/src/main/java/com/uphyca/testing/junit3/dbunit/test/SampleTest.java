package com.uphyca.testing.junit3.dbunit.test;

import java.sql.SQLException;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;

import android.content.Context;
import android.database.DatabaseUtils;

import com.uphyca.testing.junit3.dbunit.AndroidDBTestCase;

public class SampleTest extends AndroidDBTestCase {

    private static final String CREATE_TABLE_DDL = "DROP TABLE IF EXISTS sample;\n" + "CREATE TABLE sample(_id integer primary key autoincrement not null, name VARCHAR);\n";

    @Override
    protected IDataSet getDataSet() throws Exception {
        return getFlatXmlDataSetFromRawResrouce(R.raw.sample_test);
    }

    @Override
    protected String getDatabaseName() {
        return "sample.db";
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void onCreateDatabase(Context context) {
        DatabaseUtils.createDbFromSqlStatements(context, getDatabaseName(), 1, CREATE_TABLE_DDL);
    }

    public void testPreconditions() throws SQLException,
                                   Exception {

        // Fetch database data after executing your code
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("sample");
        
        // Load expected data from an XML dataset
        IDataSet expectedDataSet = getFlatXmlDataSetFromRawResrouce(R.raw.sample_test);
        ITable expectedTable = expectedDataSet.getTable("sample");

        // Assert actual database table match expected table
        Assertion.assertEquals(expectedTable, actualTable);
    }
}
