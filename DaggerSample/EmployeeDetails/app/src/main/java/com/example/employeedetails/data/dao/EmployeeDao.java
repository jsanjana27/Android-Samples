package com.example.employeedetails.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.employeedetails.data.model.Employee;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Insert
    void createEmployee(Employee employee);

    @Insert
    void addAllEmployees(List<Employee> employee);

//    @Query("SELECT * from employee_table WHERE Active='true' ORDER BY FirstName ASC")
//    LiveData<List<Employee>> getAllEmpsWithLivedata();

    @Query("SELECT * from employee_table WHERE Active='1' ORDER BY FirstName ASC")
    List<Employee> getAllEmps();

    @Query("SELECT * from employee_table WHERE _id= :id")
    Employee getEmployeeDetails(String id);

    @Query("SELECT * from employee_table WHERE _id= :id")
    LiveData<Employee> getEmployeeDetailsAsLiveData(String id);

    @Update
    int update(Employee employee);

    @Delete
    void delete(Employee employee);

    @Query("DELETE from employee_table")
    void deleteAll();

}
