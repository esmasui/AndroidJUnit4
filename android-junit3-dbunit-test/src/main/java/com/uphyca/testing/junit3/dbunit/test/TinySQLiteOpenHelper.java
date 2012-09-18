package com.uphyca.testing.junit3.dbunit.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TinySQLiteOpenHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "tiny.db";
    static final int DATABASE_VERSION = 1;
    static final String TABLE_TINY = "tiny";

    public TinySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_TABLE_DDL = "CREATE TABLE tiny(_id integer primary key autoincrement not null, name VARCHAR);\n";
    private static final String DROP_TABLE_DDL = "DROP TABLE IF EXISTS tiny";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DDL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion,
                          int newVersion) {
        db.execSQL(DROP_TABLE_DDL);
        onCreate(db);
    }

}
