package com.example.employeedetails.data.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.employeedetails.data.model.Organisation;

import java.util.List;

@Dao
public interface OrganisationDao {

    @Insert
    void addOrganisation(Organisation organisation);

    @Insert
    void addAllOrgs(List<Organisation> organisation);

//    @Query("SELECT * from org_table ORDER BY name ASC")
//    LiveData<List<Organisation>> getAllOrgs();

    @Query("SELECT * from org_table WHERE isActive='1' ORDER BY name ASC")
    List<Organisation> getAllOrgs();

    @Query("SELECT * from org_table WHERE _id = :id")
    Organisation getOrgById(String id);

    @Query("SELECT * from org_table WHERE _id= :id")
    LiveData<Organisation> getOrgDetailsAsLiveData(String id);

    @Update
    int update(Organisation organisation);

    @Delete
    void delete(Organisation organisation);

    @Query("DELETE from org_table")
    void deleteAll();
}
