package com.example.employeedetails.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.employeedetails.data.model.Organisation;
import com.example.employeedetails.data.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void addTask(Task task);

    @Insert
    void addAllTasks(List<Task> tasks);

    @Query("SELECT * from task_table ORDER BY name ASC")
    LiveData<List<Task>> getAllTasks();

    @Query("SELECT * from task_table WHERE _id = :id")
    Task getTaskById(String id);

    @Query("SELECT * from task_table WHERE _id= :id")
    LiveData<Task> getTaskDetailsAsLiveData(String id);

    @Update
    int update(Task task);

    @Delete
    void delete(Task task);

    @Query("DELETE from task_table")
    void deleteAll();
}
