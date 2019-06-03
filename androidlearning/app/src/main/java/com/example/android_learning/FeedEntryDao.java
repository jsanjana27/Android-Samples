package com.example.android_learning;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import static android.icu.text.MessagePattern.ArgType.SELECT;

public class FeedEntryDao {

    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + " TEXT, " +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL + " TEXT, " +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS + " TEXT)";

    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;

    public void insert(String name, String number, String email, String address) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, name);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, number);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL, email);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS, address);

        FeedReaderDbHelper.getInstance().getDB().insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, contentValues);
    }

    public List<DatabaseModel> getAllDetails() {
        List dbList = new ArrayList<DatabaseModel>();
        Cursor cursor = FeedReaderDbHelper.getInstance().getDB().query(FeedReaderContract.FeedEntry.TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            DatabaseModel dbModel = new DatabaseModel();

            long id = cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID));
            dbModel.setId(id);

            String name = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE));
            dbModel.setName(name);

            String number = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE));
            dbModel.setNumber(number);

            String email = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL));
            dbModel.setEmail(email);

            String address = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS));
            dbModel.setAddress(address);

            dbList.add(dbModel);
        }

        return dbList;
    }

    public DatabaseModel getDetailsById(long id) {
        String selection = FeedReaderContract.FeedEntry._ID + " = ?";
        String[] selectionArg = new String[]{String.valueOf(id)};
        Cursor cursor = FeedReaderDbHelper.getInstance().getDB().query(FeedReaderContract.FeedEntry.TABLE_NAME, null, selection, selectionArg, null, null, null);

        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }
        cursor.moveToFirst();
        DatabaseModel dbModel = new DatabaseModel();

        dbModel.setId(id);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE));
        dbModel.setName(name);

        String number = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE));
        dbModel.setNumber(number);

        String email = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL));
        dbModel.setEmail(email);

        String address = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS));
        dbModel.setAddress(address);

        return dbModel;
    }

    public void update(long id, String name, String number, String email, String address) {
        String selection = FeedReaderContract.FeedEntry._ID + " = ?";
        String[] selectionArg = new String[]{String.valueOf(id)};

        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, name);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, number);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL, email);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS, address);

        FeedReaderDbHelper.getInstance().getDB().update(FeedReaderContract.FeedEntry.TABLE_NAME, contentValues, selection, selectionArg);

    }

    public void delete(long id){
        String selection = FeedReaderContract.FeedEntry._ID + " = ?";
        String[] selectionArg = new String[]{String.valueOf(id)};

        FeedReaderDbHelper.getInstance().getDB().delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selectionArg);
    }
public void deleteAll() {
        FeedReaderDbHelper.getInstance().getDB().delete(FeedReaderContract.FeedEntry.TABLE_NAME, "1",null);
}

}
