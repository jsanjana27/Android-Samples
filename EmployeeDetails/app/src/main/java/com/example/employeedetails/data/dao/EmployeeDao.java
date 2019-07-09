package com.example.employeedetails.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import com.example.employeedetails.data.response.Employee;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Query("SELECT * from employee_table ORDER BY FirstName ASC")
    LiveData<List<Employee>> getAllEmps();

    @Query("SELECT * from employee_table WHERE Username= :username")
    Employee getEmployeeDetails(String username);

    @Update
    int update(Employee employeeResponse);

}
