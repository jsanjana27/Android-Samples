package com.example.employeedetails.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "task_table")
public class Task implements Serializable {
    @PrimaryKey()
    @ColumnInfo(name = "primaryId")
    @SerializedName("taskId")
    private long primaryId;
    @ColumnInfo(name = "_id")
    @SerializedName("_id")
    private String taskId;
    @ColumnInfo(name = "Name")
    private String name;
    @ColumnInfo(name = "Description")
    private String description;

    public long getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(long primaryId) {
        this.primaryId = primaryId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
