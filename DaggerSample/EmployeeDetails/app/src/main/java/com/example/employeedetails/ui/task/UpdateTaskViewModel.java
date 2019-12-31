package com.example.employeedetails.ui.task;

import android.text.TextUtils;
import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.employeedetails.data.model.Task;

public class UpdateTaskViewModel extends ViewModel {

    public ObservableField<String> taskId = new ObservableField<String>();
    public ObservableField<String> taskIdError = new ObservableField<String>();
    public ObservableField<String> name = new ObservableField<String>();
    public ObservableField<String> nameError = new ObservableField<String>();
    public ObservableField<String> description = new ObservableField<String>();

    String id = null;

    MutableLiveData<Task> task = new MutableLiveData<>();
    private TaskRepository taskRepository = new TaskRepository();

    LiveData<Task> updateResponse = Transformations.switchMap(task, new Function<Task, LiveData<Task>>() {
        @Override
        public LiveData<Task> apply(Task input) {
            return taskRepository.updateTaskDetails(input);
        }
    });



    public LiveData<Task> getTaskById(String id) {
        this.id = id;
        return taskRepository.getTaskById(id);
    }

    public LiveData<Boolean> deleteTaskById(String id) {
        return taskRepository.deleteTaskById(id);
    }

    public void onUpdateTask(Task task) {
        name.set(task.getName());
        taskId.set(task.getPrimaryId() + "");
        description.set(task.getDescription());
    }

    public void onTaskUpdate() {
        if (TextUtils.isEmpty(name.get())) {
            nameError.set("Please enter task name");

            return;
        }
        nameError.set(null);

        Task task = new Task();
        task.setTaskId(id);
        task.setName(name.get());
        task.setPrimaryId(Long.parseLong(taskId.get()));
        task.setDescription(description.get());

        this.task.setValue(task);

    }
}
