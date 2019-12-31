package com.example.employeedetails.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.employeedetails.data.model.Organisation;
import com.example.employeedetails.data.model.Project;

import java.util.List;

@Dao
public interface ProjectDao {
    @Insert
    void addProject(Project project);

    @Insert
    void addAllProjects(List<Project> projects);

    @Query("SELECT * from project_table ORDER BY name ASC")
    LiveData<List<Project>> getAllProjects();

    @Query("SELECT * from project_table WHERE _id = :id")
    Project getProjectById(String id);

    @Query("SELECT * from project_table WHERE _id= :id")
    LiveData<Project> getProjectDetailsAsLiveData(String id);

    @Update
    int update(Project project);

    @Delete
    void delete(Project project);

    @Query("DELETE from project_table")
    void deleteAll();
}
