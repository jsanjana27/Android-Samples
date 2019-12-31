package com.example.employeedetails.ui.employee;

import com.example.employeedetails.data.model.Employee;

public class EmployeeResponse {

   private Employee user;

   private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Employee getUser() {
        return user;
    }

    public void setUser(Employee user) {
        this.user = user;
    }
}
