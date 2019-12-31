package com.example.employeedetails.ui.project;

import android.text.TextUtils;
import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.employeedetails.data.model.Organisation;
import com.example.employeedetails.data.model.Project;
import com.example.employeedetails.data.model.Task;

public class UpdateProjectViewModel extends ViewModel {
    public ObservableField<String> name = new ObservableField<String>();
    public ObservableField<String> nameError = new ObservableField<String>();
    public ObservableField<String> description = new ObservableField<String>();
    public ObservableField<String> startDate = new ObservableField<String>();
    public ObservableField<String> startDateError = new ObservableField<String>();
    public ObservableField<String> endDate = new ObservableField<String>();
    public ObservableField<String> endDateError = new ObservableField<String>();
    public ObservableField<String> manager = new ObservableField<String>();
    public ObservableField<String> projectId = new ObservableField<String>();
    public ObservableField<Boolean> isActive = new ObservableField<Boolean>();


    MutableLiveData<Project> project = new MutableLiveData<>();
    String id = null;
    private ProjectRepository projectRepository = new ProjectRepository();
    LiveData<Project> updateResponse = Transformations.switchMap(project, new Function<Project, LiveData<Project>>() {
        @Override
        public LiveData<Project> apply(Project input) {
            return projectRepository.updateProjectDetails(input);
        }
    });
    public LiveData<Boolean> deleteResponse = Transformations.switchMap(project, new Function<Project, LiveData<Boolean>>() {
        @Override
        public LiveData<Boolean> apply(Project input) {
            return projectRepository.deleteProjectById(input.getProjectId());
        }
    });

    public LiveData<Project> getProjectById(String id) {
        this.id = id;
        return projectRepository.getProjectById(id);
    }

    public LiveData<Boolean> deleteProjectById(String id) {
        return projectRepository.deleteProjectById(id);
    }

    public void onUpdateProject(Project project) {
        name.set(project.getName());
        projectId.set(project.getPrimaryId() + "");
        description.set(project.getDescription());
        manager.set(project.getManager());
        startDate.set(project.getStartDate());
        endDate.set(project.getEndDate());
        isActive.set(project.getActive());
    }

    public void onProjectUpdate() {
        if (TextUtils.isEmpty(name.get())) {
            nameError.set("Please enter project name");

            return;
        }
        nameError.set(null);

        if (TextUtils.isEmpty(startDate.get())) {
            startDateError.set("Please enter project start date");

            return;
        }
        startDateError.set(null);
        if (TextUtils.isEmpty(endDate.get())) {
            endDateError.set("Please enter project end date");

            return;
        }
        endDateError.set(null);

        Project project = new Project();
        project.setName(name.get());
        project.setPrimaryId(Long.parseLong(projectId.get()));
        project.setProjectId(id);
        project.setActive(isActive.get());
        project.setStartDate(startDate.get());
        project.setEndDate(endDate.get());
        project.setDescription(description.get());
        project.setManager(manager.get());

        this.project.setValue(project);
    }
}
