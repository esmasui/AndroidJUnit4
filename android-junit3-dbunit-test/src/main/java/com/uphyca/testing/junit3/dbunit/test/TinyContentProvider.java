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

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class TinyContentProvider extends ContentProvider {

    public static final String AUTHORITY = "com.uphyca.testing.dbunit";
    private static final String BASE_PATH = "tests";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    private static final int TESTS = 10;
    private static final int TEST_ID = 20;

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/tests";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/test";

    private static final UriMatcher sUriMatcher = buildUriMather();

    private static UriMatcher buildUriMather() {
        UriMatcher mt = new UriMatcher(UriMatcher.NO_MATCH);
        mt.addURI(AUTHORITY,
                  BASE_PATH,
                  TESTS);
        mt.addURI(AUTHORITY,
                  BASE_PATH + "/#",
                  TEST_ID);

        return mt;
    }

    private SQLiteOpenHelper mSQLite;

    @Override
    public boolean onCreate() {

        mSQLite = new TinySQLiteOpenHelper(getContext());

        return false;
    }

    @Override
    public Cursor query(Uri uri,
                        String[] projection,
                        String selection,
                        String[] selectionArgs,
                        String sortOrder) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(TinySQLiteOpenHelper.TABLE_TINY);

        int uriType = sUriMatcher.match(uri);
        switch (uriType) {
        case TESTS:
            break;
        case TEST_ID:
            builder.appendWhere("_id" + "=" + uri.getLastPathSegment());
            break;
        default:
            throw new IllegalArgumentException("Unknown URI:" + uri);
        }

        SQLiteDatabase db = mSQLite.getWritableDatabase();
        Cursor cursor = builder.query(db,
                                      projection,
                                      selection,
                                      selectionArgs,
                                      null,
                                      null,
                                      sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),
                                  uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        int uriType = sUriMatcher.match(uri);
        switch (uriType) {
        case TESTS:
            return CONTENT_TYPE;
        case TEST_ID:
            return CONTENT_ITEM_TYPE;
        default:
            throw new IllegalArgumentException("Unknown URI:" + uri);
        }
    }

    @Override
    public Uri insert(Uri uri,
                      ContentValues values) {
        SQLiteDatabase db = mSQLite.getWritableDatabase();
        long id = 0;
        int uriType = sUriMatcher.match(uri);
        switch (uriType) {
        case TESTS:
            id = db.insert(TinySQLiteOpenHelper.TABLE_TINY,
                           null,
                           values);
            break;
        default:
            throw new IllegalArgumentException("Unknown URI:" + uri);
        }
        getContext().getContentResolver().notifyChange(uri,
                                                       null);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri,
                      String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = mSQLite.getWritableDatabase();
        int rowsDeleted = 0;
        int uriType = sUriMatcher.match(uri);
        switch (uriType) {
        case TESTS:
            rowsDeleted = db.delete(TinySQLiteOpenHelper.TABLE_TINY,
                                    selection,
                                    selectionArgs);
            break;
        case TEST_ID:
            String id = uri.getLastPathSegment();
            if (TextUtils.isEmpty(selection)) {
                rowsDeleted = db.delete(TinySQLiteOpenHelper.TABLE_TINY,
                                        "_id" + "=" + id,
                                        null);
            } else {
                rowsDeleted = db.delete(TinySQLiteOpenHelper.TABLE_TINY,
                                        "_id" + "=" + id + " and " + selection,
                                        selectionArgs);
            }
            break;
        default:
            throw new IllegalArgumentException("Unknown URI:" + uri);
        }
        getContext().getContentResolver().notifyChange(uri,
                                                       null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri,
                      ContentValues values,
                      String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = mSQLite.getWritableDatabase();
        int rowsUpdated = 0;
        int uriType = sUriMatcher.match(uri);
        switch (uriType) {
        case TESTS:
            rowsUpdated = db.update(TinySQLiteOpenHelper.TABLE_TINY,
                                    values,
                                    selection,
                                    selectionArgs);
            break;
        case TEST_ID:
            String id = uri.getLastPathSegment();
            if (TextUtils.isEmpty(selection)) {
                rowsUpdated = db.update(TinySQLiteOpenHelper.TABLE_TINY,
                                        values,
                                        "_id" + "=" + id,
                                        null);
            } else {
                rowsUpdated = db.update(TinySQLiteOpenHelper.TABLE_TINY,
                                        values,
                                        "_id" + "=" + id + " and " + selection,
                                        selectionArgs);
            }
            break;
        default:
            throw new IllegalArgumentException("Unknown URI:" + uri);
        }
        getContext().getContentResolver().notifyChange(uri,
                                                       null);
        return rowsUpdated;
    }

}
