package com.example.employeedetails.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.employeedetails.App;
import com.example.employeedetails.R;
import com.example.employeedetails.data.common.ApiHandler;
import com.example.employeedetails.data.dao.EmployeeDao;
import com.example.employeedetails.data.local.EmployeeRoomDatabase;
import com.example.employeedetails.data.response.Employee;
import com.example.employeedetails.ui.main.DashboardActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private EmployeeDao mEmployeeDao;
    private LiveData<List<Employee>> mEmployee;
    private Context context;

    public AuthRepository() {
        EmployeeRoomDatabase db = EmployeeRoomDatabase.getInstance();
        mEmployeeDao = db.employeeDao();
        mEmployee = mEmployeeDao.getAllEmps();
    }

    public LiveData<List<Employee>> getAllEmployee() {
        return mEmployee;
    }

    public void updateDetails(Employee employee) {
        mEmployeeDao.update(employee);
    }

    public Employee getEmpDetails(String username) {
        return mEmployeeDao.getEmployeeDetails(username);
    }

    public LiveData<Boolean> login(String userName, String password) {
        MutableLiveData<Boolean> result = new MutableLiveData<Boolean>();

        JSONObject object = new JSONObject();
        try {
            object.put("username", userName);
            object.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; utf-8"),object.toString());


        Call<Employee> call = ApiHandler.getService().employeeLogin(requestBody);

        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (response.isSuccessful()) {

                    Employee employee = response.body();
                    Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]" + employee.getToken());

                    SharedPreferences sharedPreferences = App.getApp().getSharedPreferences(App.getApp().getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", userName);
                    editor.putString("password", password);
                    editor.putString("token", "Bearer " + employee.getToken());
                    editor.commit();
                    result.postValue(true);
                }

            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                result.postValue(false);

            }
        });

        return result;
    }
}
