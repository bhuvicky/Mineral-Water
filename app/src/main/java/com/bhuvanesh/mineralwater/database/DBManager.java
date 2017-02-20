package com.bhuvanesh.mineralwater.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.ParcelUuid;

import com.bhuvanesh.mineralwater.util.LoggerUtil;

/**
 * Created by bhuvanesh on 01-02-2017.
 */

public class DBManager implements DBQuery {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Mineral Water";

    private SQLiteDatabase mSQLiteDatabase;
    private SLDBHelper mSLDBHelper;

    public DBManager(Context context) {
        LoggerUtil.println("log db manager constructor");
        mSLDBHelper = SLDBHelper.getHelper(context);
    }


//     Open the database

    public DBManager connect() {
        if (mSQLiteDatabase == null)
            mSQLiteDatabase = mSLDBHelper.getWritableDatabase();
        return this;
    }

//    It returns the current database object

    public SQLiteDatabase getDBInstance() {
        if (mSQLiteDatabase == null)
            mSQLiteDatabase = mSLDBHelper.getWritableDatabase();
        return mSQLiteDatabase;
    }

    public long getTableRowCount(String tableName) {
        return DatabaseUtils.queryNumEntries(getDBInstance(), tableName);
    }

    public void printCursor(Cursor cursor) {
        DatabaseUtils.dumpCursor(cursor);
    }

    public long replace(String tableName, ContentValues values) {
        long rowId = 0L;
        SQLiteDatabase sqlDb = getDBInstance();
        sqlDb.beginTransaction();

        try {
            rowId = sqlDb.replace(tableName, null, values);
        } finally {
            sqlDb.setTransactionSuccessful();
            sqlDb.endTransaction();
        }
        return rowId;
    }

    public Cursor select(String query) {
        return select(query, null);
    }

    public Cursor select(String query, String[] selectionArgs) {
        return getDBInstance().rawQuery(query, selectionArgs);
    }

    private static class SLDBHelper extends SQLiteOpenHelper {

        private static SLDBHelper mSLDBHelper;

        private SLDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            LoggerUtil.println("log so, SQLiteOpenHelper constructor get called");
        }

        public static synchronized SLDBHelper getHelper(Context context) {
            if (mSLDBHelper == null) {
                LoggerUtil.println("log mSLDBHelper null");
                mSLDBHelper = new SLDBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
            }
            else
                LoggerUtil.println("log mSLDBHelper not null, so, SQLiteOpenHelper constructor won't call");
            return mSLDBHelper;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            LoggerUtil.println("log mSLDBHelper onCreate called");
            db.execSQL(CREATE_TABLE_PROFILE);

//
            db.execSQL(ALTER_TABLE_PROFILE_VERSION_2);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            LoggerUtil.println("log old v = " + oldVersion + " new ver = " + newVersion);
                    db.execSQL(ALTER_TABLE_PROFILE_VERSION_2);
        }
    }
}
