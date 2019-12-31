package com.example.employeedetails.ui.project;

import com.example.employeedetails.data.model.Project;

public class ProjectResponse {

    private Project project;
    private String token;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
