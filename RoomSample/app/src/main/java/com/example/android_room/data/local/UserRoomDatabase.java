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

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE user_table "
                    + "ADD Photo TEXT");

        }
    };

    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE user_table "
                    + "ADD COLUMN Age INTEGER ");
            database.execSQL("ALTER TABLE user_table " + " ADD COLUMN Description TEXT ");

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

    public static void getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserRoomDatabase.class, "user_database")
                            .addMigrations(MIGRATION_1_2, MIGRATION_2_3).allowMainThreadQueries()
                            .build();
                }
            }
        }
    }

    public static UserRoomDatabase getInstance() {
        return INSTANCE;
    }

    public abstract UserDao userDao();
}


