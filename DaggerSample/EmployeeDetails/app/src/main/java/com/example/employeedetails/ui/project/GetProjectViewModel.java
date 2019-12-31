package com.example.employeedetails.ui.project;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.employeedetails.data.model.Project;

import java.util.List;

public class GetProjectViewModel extends ViewModel {

    private ProjectRepository projectRepository = new ProjectRepository();

    public LiveData<List<Project>> getAllProjects() {
        return projectRepository.getAllProjects();
    }

    public LiveData<Project> getProjectById(String id) {
        return projectRepository.getProjectById(id);
    }
}
