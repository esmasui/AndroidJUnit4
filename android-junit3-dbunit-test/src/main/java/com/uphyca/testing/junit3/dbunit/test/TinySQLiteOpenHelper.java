/*
 * Copyright (C) 2012 uPhyca Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
