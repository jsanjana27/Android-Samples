package com.example.employeedetails.ui.task;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.employeedetails.data.common.ApiHandler;
import com.example.employeedetails.data.local.RoomDatabase;
import com.example.employeedetails.data.model.Task;
import com.example.employeedetails.util.PreferenceUtil;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskRepository {
    private static final String TAG = TaskRepository.class.getSimpleName();
    private LiveData<List<Task>> mTask;


    public LiveData<Task> addTask(Task task) {
        MutableLiveData<Task> result = new MutableLiveData<Task>();

        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Accept", "application/json");
        header.put("authorization", "Bearer " + PreferenceUtil.getInstance().getEncStringValue("token", ""));
        Call<Task> call = ApiHandler.getService().addTask(header, task);

        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if (response.isSuccessful()) {
                    Log.d("test", "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                    RoomDatabase.getInstance().taskDao().addTask(task);
                    Task task = response.body();
                    result.postValue(task);
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                result.postValue(task);
            }
        });
        return result;

    }

    public LiveData<List<Task>> getAllTasks() {
        MutableLiveData<List<Task>> result = new MutableLiveData<List<Task>>();

        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Accept", "application/json");
        header.put("authorization", "Bearer " + PreferenceUtil.getInstance().getEncStringValue("token", ""));

        Call<List<Task>> call = ApiHandler.getService().getAllTasks(header);

        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if (response.isSuccessful()) {
                    RoomDatabase.getInstance().taskDao().deleteAll();

                    List<Task> tasks = response.body();
                    RoomDatabase.getInstance().taskDao().addAllTasks(tasks);
                    result.setValue(tasks);
                }
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                RoomDatabase.getInstance().taskDao().getAllTasks();

                result.postValue(null);
            }
        });
        return result;
    }

    public LiveData<Task> updateTaskDetails(Task task) {
        MutableLiveData<Task> result = new MutableLiveData<Task>();

        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Accept", "application/json");
        header.put("authorization", "Bearer " + PreferenceUtil.getInstance().getEncStringValue("token", ""));

        Call<Task> call = ApiHandler.getService().updateTaskDetails(header, task.getTaskId(), task);

        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if (response.isSuccessful()) {
                    RoomDatabase.getInstance().taskDao().update(task);
                    result.postValue(task);
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                result.postValue(null);
            }
        });
        return result;
    }

    public LiveData<Task> getTaskById(String id) {

        return RoomDatabase.getInstance().taskDao().getTaskDetailsAsLiveData(id);

    }

    public LiveData<Boolean> deleteTaskById(String id) {
        MutableLiveData<Boolean> result = new MutableLiveData<Boolean>();

        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Accept", "application/json");
        header.put("authorization", "Bearer " + PreferenceUtil.getInstance().getEncStringValue("token", ""));


        Call<Task> call = ApiHandler.getService().deleteTaskById(header, id);

        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if (response.isSuccessful()) {
                    Task task = RoomDatabase.getInstance().taskDao().getTaskById(id);
                    RoomDatabase.getInstance().taskDao().delete(task);
                    result.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                result.postValue(false);
            }
        });
        return result;
    }

}
