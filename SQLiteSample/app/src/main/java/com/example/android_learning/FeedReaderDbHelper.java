package com.example.android_learning;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.android_learning.FeedEntryDao.SQL_CREATE_ENTRIES;
import static com.example.android_learning.FeedEntryDao.SQL_DELETE_ENTRIES;

public class FeedReaderDbHelper {

    private static FeedReaderDbHelper mInstance;

    private SQLiteDatabase mDatabase;

    private FeedReaderDbHelper(Context context) {
        mDatabase = new FeedDb(context).getWritableDatabase();
    }

    public static void init(Context context) {
        mInstance = new FeedReaderDbHelper(context);
    }

    public static FeedReaderDbHelper getInstance() {
        return mInstance;
    }

    public SQLiteDatabase getDB() {
        return mDatabase;
    }

    class FeedDb extends SQLiteOpenHelper {


        public static final String DATABASE_NAME = "FeedReader.db";
        public static final int DATABASE_VERSION = 1;


        public FeedDb(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL(SQL_DELETE_ENTRIES);
//        onCreate(db);
        }

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        onUpgrade(db, oldVersion, newVersion);
        }
    }


}
