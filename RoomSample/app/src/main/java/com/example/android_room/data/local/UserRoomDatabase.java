package com.example.android_room.data.local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.android_room.data.local.dao.UserDao;
import com.example.android_room.data.model.DatabaseModel;

@Database(entities = DatabaseModel.class, version = 2, exportSchema = false)
public abstract class UserRoomDatabase extends RoomDatabase {

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE user_table "
                    + "ADD Photo TEXT");

        }
    };
    private static volatile UserRoomDatabase INSTANCE;
    private static Callback sRoomDatabaseCallback =
            new Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
//                    new UserRepository.PopulateDbAsync(INSTANCE).execute();
                }
            };

    public static UserRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserRoomDatabase.class, "user_database")
                            .addCallback(sRoomDatabaseCallback).allowMainThreadQueries()
                            .addMigrations(MIGRATION_1_2)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static UserRoomDatabase getInstance() {
        return INSTANCE;
    }

    public abstract UserDao userDao();
}


