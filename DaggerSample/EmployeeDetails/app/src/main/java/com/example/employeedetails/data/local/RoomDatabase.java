package com.example.employeedetails.data.local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.employeedetails.data.dao.EmployeeDao;
import com.example.employeedetails.data.dao.OrganisationDao;
import com.example.employeedetails.data.dao.ProjectDao;
import com.example.employeedetails.data.dao.TaskDao;
import com.example.employeedetails.data.model.Employee;
import com.example.employeedetails.data.model.Organisation;
import com.example.employeedetails.data.model.Project;
import com.example.employeedetails.data.model.Task;

@Database(entities = {Employee.class, Organisation.class, Project.class, Task.class}, version = 1, exportSchema = false)

public abstract class RoomDatabase extends androidx.room.RoomDatabase {

    private static volatile RoomDatabase INSTANCE;
    private static Callback sRoomDatabaseCallback =
            new Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                }
            };

    public static RoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDatabase.class, "employee_database")
                            .addCallback(sRoomDatabaseCallback).allowMainThreadQueries()
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    public static RoomDatabase getInstance() {
        return INSTANCE;
    }

    public abstract EmployeeDao employeeDao();

    public abstract OrganisationDao organisationDao();

    public abstract ProjectDao projectDao();

    public abstract TaskDao taskDao();
}
