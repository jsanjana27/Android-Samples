package com.example.employeedetails.ui.project;

import android.text.TextUtils;
import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.employeedetails.data.model.Project;

import java.util.List;

public class AddProjectViewModel extends ViewModel {

    public ObservableField<String> name = new ObservableField<String>();
    public ObservableField<String> nameError = new ObservableField<String>();
    public ObservableField<String> description = new ObservableField<String>();
    public ObservableField<String> startDate = new ObservableField<String>();
    public ObservableField<String> startDateError = new ObservableField<String>();
    public ObservableField<String> endDate = new ObservableField<String>();
    public ObservableField<String> endDateError = new ObservableField<String>();
    public ObservableField<String> manager = new ObservableField<String>();
    public ObservableField<String> projectId = new ObservableField<String>();
    public ObservableField<String> projectIdError = new ObservableField<String>();
    public ObservableField<Boolean> isActive = new ObservableField<Boolean>();


    MutableLiveData<Project> project = new MutableLiveData<>();
    private ProjectRepository projectRepository = new ProjectRepository();

    LiveData<Project> addResponse = Transformations.switchMap(project, new Function<Project, LiveData<Project>>() {
        @Override
        public LiveData<Project> apply(Project input) {
            return projectRepository.addProject(input);
        }
    });

    public void addProject(Project project) {
        this.project.setValue(project);
    }

    public LiveData<Project> updateProjectDetails(Project project) {
        return projectRepository.updateProjectDetails(project);
    }

    public LiveData<Boolean> deleteProjectById(String id) {
        return projectRepository.deleteProjectById(id);
    }

    public void onAddProjectClicked() {
        if (TextUtils.isEmpty(projectId.get())) {
            projectIdError.set("Please enter project id");
            return;
        }
        projectIdError.set(null);

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
        project.setPrimaryId(Long.parseLong(projectId.get()));
        project.setName(name.get());
        project.setDescription(description.get());
        project.setStartDate(startDate.get());
        project.setEndDate(endDate.get());
        project.setManager(manager.get());
        project.setActive(isActive.get());

        this.project.setValue(project);
    }
}
