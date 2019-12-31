package com.example.employeedetails.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "org_table")
public class Organisation implements Serializable {

    @PrimaryKey()
    @ColumnInfo(name = "primaryId")
    @SerializedName("orgId")
    private long primaryId;
    @ColumnInfo(name = "_id")
    @SerializedName("_id")
    private String orgId;
    @ColumnInfo(name = "Name")
    private String name;
    @ColumnInfo(name = "isActive")
    private Boolean isActive;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public long getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(long primaryId) {
        this.primaryId = primaryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
