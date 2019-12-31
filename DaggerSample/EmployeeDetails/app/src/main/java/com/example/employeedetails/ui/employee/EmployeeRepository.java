package com.example.employeedetails.ui.employee;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.employeedetails.data.common.ApiHandler;
import com.example.employeedetails.data.local.RoomDatabase;
import com.example.employeedetails.data.model.Employee;
import com.example.employeedetails.util.PreferenceUtil;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeRepository {
    private static final String TAG = EmployeeRepository.class.getSimpleName();
    private LiveData<List<Employee>> mEmployee;

    public LiveData<Employee> register(Employee employee) {
        MutableLiveData<Employee> result = new MutableLiveData<Employee>();

        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Accept", "application/json");
        header.put("authorization", "Bearer " + PreferenceUtil.getInstance().getEncStringValue("token", ""));
        Call<Employee> call = ApiHandler.getService().createEmployee(header, employee);

        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (response.isSuccessful()) {
                    Log.d("test", "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                    RoomDatabase.getInstance().employeeDao().createEmployee(employee);
                    Employee employee = response.body();
                    result.postValue(employee);
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                result.postValue(employee);
            }
        });
        return result;

    }

    public LiveData<List<Employee>> getAllEmployees() {
        MutableLiveData<List<Employee>> result = new MutableLiveData<List<Employee>>();

        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Accept", "application/json");
        header.put("authorization", "Bearer " + PreferenceUtil.getInstance().getEncStringValue("token", ""));

        Call<List<Employee>> call = ApiHandler.getService().getAllEmployees(header);

        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (response.isSuccessful()) {
                    RoomDatabase.getInstance().employeeDao().deleteAll();

                    List<Employee> employees = response.body();
                    RoomDatabase.getInstance().employeeDao().addAllEmployees(employees);

                    result.setValue(RoomDatabase.getInstance().employeeDao().getAllEmps());
                }else {
                    result.setValue(RoomDatabase.getInstance().employeeDao().getAllEmps());

                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {

                result.setValue(RoomDatabase.getInstance().employeeDao().getAllEmps());
            }
        });
        return result;
    }

    public LiveData<Employee> updateDetails(Employee employee) {
        MutableLiveData<Employee> result = new MutableLiveData<Employee>();

        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Accept", "application/json");
        header.put("authorization", "Bearer " + PreferenceUtil.getInstance().getEncStringValue("token", ""));

        Call<Employee> call = ApiHandler.getService().updateDetails(header, employee.getUserId(), employee);

        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                if (response.isSuccessful()) {
                    RoomDatabase.getInstance().employeeDao().update(employee);
                    result.postValue(employee);
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                result.postValue(null);
            }
        });
        return result;
    }

    public LiveData<Employee> getEmployeeById(String id) {

        return RoomDatabase.getInstance().employeeDao().getEmployeeDetailsAsLiveData(id);

    }

    public LiveData<Boolean> deleteById(String id) {
        MutableLiveData<Boolean> result = new MutableLiveData<Boolean>();

        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Accept", "application/json");
        header.put("authorization", "Bearer " + PreferenceUtil.getInstance().getEncStringValue("token", ""));


        Call<Employee> call = ApiHandler.getService().deleteById(header, id);

        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (response.isSuccessful()) {
                    Employee employee = RoomDatabase.getInstance().employeeDao().getEmployeeDetails(id);
                    RoomDatabase.getInstance().employeeDao().delete(employee);
                    result.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                result.postValue(false);
            }
        });
        return result;
    }
}
