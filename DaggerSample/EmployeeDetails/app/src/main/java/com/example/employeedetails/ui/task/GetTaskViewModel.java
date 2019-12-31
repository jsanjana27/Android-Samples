package com.example.employeedetails.ui.task;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.employeedetails.data.model.Task;

import java.util.List;

public class GetTaskViewModel extends ViewModel {

    private TaskRepository taskRepository = new TaskRepository();

    public LiveData<List<Task>> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public LiveData<Task> getTaskById(String id) {
        return taskRepository.getTaskById(id);
    }
}
