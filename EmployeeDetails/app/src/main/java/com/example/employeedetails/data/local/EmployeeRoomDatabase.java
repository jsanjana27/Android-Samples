package com.example.employeedetails.data.local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.employeedetails.data.dao.EmployeeDao;
import com.example.employeedetails.data.response.Employee;

@Database(entities = Employee.class, version = 1, exportSchema = false)

public abstract class EmployeeRoomDatabase extends RoomDatabase {

    private static volatile EmployeeRoomDatabase INSTANCE;
    private static Callback sRoomDatabaseCallback =
            new Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                }
            };

    public static EmployeeRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EmployeeRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            EmployeeRoomDatabase.class, "employee_database")
                            .addCallback(sRoomDatabaseCallback).allowMainThreadQueries()
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    public static EmployeeRoomDatabase getInstance() {
        return INSTANCE;
    }

    public abstract EmployeeDao employeeDao();
}
