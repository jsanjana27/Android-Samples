package com.example.employeedetails.ui.employee;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.employeedetails.data.model.Employee;

import java.util.List;

public class GetEmployeeViewModel extends ViewModel {

    private EmployeeRepository employeeRepository = new EmployeeRepository();

    public LiveData<List<Employee>> getEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public LiveData<Employee> getEmployeeById(String id) {
        return employeeRepository.getEmployeeById(id);
    }
}
