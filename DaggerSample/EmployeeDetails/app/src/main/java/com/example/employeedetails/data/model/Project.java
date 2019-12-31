package com.example.employeedetails.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "project_table")

public class Project implements Serializable {

    @PrimaryKey()
    @ColumnInfo(name = "primaryId")
    @SerializedName("projectId")
    private long primaryId;
    @ColumnInfo(name = "_id")
    @SerializedName("_id")
    private String projectId;
    @ColumnInfo(name = "Name")
    private String name;
    @ColumnInfo(name = "Description")
    private String description;
    @ColumnInfo(name = "StartDate")
    @SerializedName("startDate")
    private String startDate;
    @ColumnInfo(name = "EndDate")
    @SerializedName("endDate")
    private String endDate;
    @ColumnInfo(name = "Manager")
    private String manager;
    @ColumnInfo(name = "isActive")
    private Boolean isActive;

    public long getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(long primaryId) {
        this.primaryId = primaryId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
