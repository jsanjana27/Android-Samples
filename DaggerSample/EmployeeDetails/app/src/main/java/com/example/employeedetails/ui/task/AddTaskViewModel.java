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

import java.util.List;

public class AddTaskViewModel extends ViewModel {

    public ObservableField<String> taskId = new ObservableField<String>();
    public ObservableField<String> taskIdError = new ObservableField<String>();
    public ObservableField<String> name = new ObservableField<String>();
    public ObservableField<String> nameError = new ObservableField<String>();
    public ObservableField<String> description = new ObservableField<String>();

    MutableLiveData<Task> task = new MutableLiveData<>();
    private TaskRepository taskRepository = new TaskRepository();

    LiveData<Task> addResponse = Transformations.switchMap(task, new Function<Task, LiveData<Task>>() {
        @Override
        public LiveData<Task> apply(Task input) {
            return taskRepository.addTask(input);
        }
    });

    public void addTask(Task task) {
        this.task.setValue(task);
    }

    public void onAddTaskClicked() {
        Log.d("task", "onAddTaskClicked: ");

        if (TextUtils.isEmpty(taskId.get())) {
            taskIdError.set("Please enter task id");
            return;
        }
        taskIdError.set(null);
        if (TextUtils.isEmpty(name.get())) {
            nameError.set("Please enter task name");

            return;
        }
        nameError.set(null);

        Task task = new Task();
        task.setPrimaryId(Long.parseLong(taskId.get()));
        task.setName(name.get());
        task.setDescription(description.get());

        this.task.setValue(task);
    }


}
