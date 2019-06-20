package com.example.android_room.data.local.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_room.data.model.DatabaseModel;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(DatabaseModel dbModel);

    @Query("DELETE from user_table")
    void deleteAll();

    @Query("SELECT * from user_table ORDER BY name ASC")
    LiveData<List<DatabaseModel>> getAllDetails();

    @Update
    int update(DatabaseModel databaseModel);

    @Delete
    void delete(DatabaseModel databaseModel);

    @Query("SELECT * from user_table WHERE ID= :id")
    DatabaseModel getDetailstById(long id);
}
