package com.example.employeedetails.ui.task;

import com.example.employeedetails.data.model.Task;

public class TaskResponse {

    private Task task;
    private String token;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
