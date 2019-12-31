package com.example.employeedetails.ui.project;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.employeedetails.data.common.ApiHandler;
import com.example.employeedetails.data.local.RoomDatabase;
import com.example.employeedetails.data.model.Project;
import com.example.employeedetails.util.PreferenceUtil;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectRepository {
    private static final String TAG = ProjectRepository.class.getSimpleName();
    private LiveData<List<Project>> mProject;

    public LiveData<Project> addProject(Project project) {
        MutableLiveData<Project> result = new MutableLiveData<Project>();

        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Accept", "application/json");
        header.put("authorization", "Bearer " + PreferenceUtil.getInstance().getEncStringValue("token", ""));
        Call<Project> call = ApiHandler.getService().addProject(header, project);

        call.enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                if (response.isSuccessful()) {
                    Log.d("test", "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                    RoomDatabase.getInstance().projectDao().addProject(project);
                    Project project = response.body();
                    result.postValue(project);
                }
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                result.postValue(project);
            }
        });
        return result;
    }

    public LiveData<List<Project>> getAllProjects() {
        MutableLiveData<List<Project>> result = new MutableLiveData<List<Project>>();

        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Accept", "application/json");
        header.put("authorization", "Bearer " + PreferenceUtil.getInstance().getEncStringValue("token", ""));

        Call<List<Project>> call = ApiHandler.getService().getAllProjects(header);

        call.enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                if (response.isSuccessful()) {
                    RoomDatabase.getInstance().projectDao().deleteAll();

                    List<Project> projects = response.body();
                    RoomDatabase.getInstance().projectDao().addAllProjects(projects);
                    result.setValue(projects);
                }
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                RoomDatabase.getInstance().projectDao().getAllProjects();

                result.postValue(null);
            }
        });
        return result;
    }

    public LiveData<Project> updateProjectDetails(Project project) {
        MutableLiveData<Project> result = new MutableLiveData<Project>();

        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Accept", "application/json");
        header.put("authorization", "Bearer " + PreferenceUtil.getInstance().getEncStringValue("token", ""));

        Call<Project> call = ApiHandler.getService().updateProjectDetails(header, project.getProjectId(), project);

        call.enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                if (response.isSuccessful()) {
                    RoomDatabase.getInstance().projectDao().update(project);
                    result.postValue(project);
                }
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                result.postValue(null);
            }
        });
        return result;
    }

    public LiveData<Project> getProjectById(String id) {

        return RoomDatabase.getInstance().projectDao().getProjectDetailsAsLiveData(id);

    }

    public LiveData<Boolean> deleteProjectById(String id) {
        MutableLiveData<Boolean> result = new MutableLiveData<Boolean>();

        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Accept", "application/json");
        header.put("authorization", "Bearer " + PreferenceUtil.getInstance().getEncStringValue("token", ""));


        Call<Project> call = ApiHandler.getService().deleteProjectById(header, id);

        call.enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                if (response.isSuccessful()) {
                    Project project = RoomDatabase.getInstance().projectDao().getProjectById(id);
                    RoomDatabase.getInstance().projectDao().delete(project);
                    result.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                result.postValue(false);
            }
        });
        return result;
    }

}
