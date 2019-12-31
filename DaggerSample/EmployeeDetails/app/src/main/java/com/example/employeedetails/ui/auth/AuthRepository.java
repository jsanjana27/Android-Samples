package com.example.employeedetails.ui.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.employeedetails.App;
import com.example.employeedetails.R;
import com.example.employeedetails.data.common.ApiHandler;
import com.example.employeedetails.data.dao.EmployeeDao;
import com.example.employeedetails.data.local.RoomDatabase;
import com.example.employeedetails.data.model.Employee;
import com.example.employeedetails.ui.employee.EmployeeResponse;
import com.example.employeedetails.util.PreferenceUtil;

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

    public AuthRepository() {
        RoomDatabase db = RoomDatabase.getInstance();
        mEmployeeDao = db.employeeDao();
    }

    public LiveData<Employee> login(String userName, String password) {
        MutableLiveData<Employee> result = new MutableLiveData<Employee>();

        JSONObject object = new JSONObject();
        try {
            object.put("username", userName);
            object.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "login: " + object.toString());
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; utf-8"), object.toString());


        Call<EmployeeResponse> call = ApiHandler.getService().employeeLogin(requestBody);

        call.enqueue(new Callback<EmployeeResponse>() {
            @Override
            public void onResponse(Call<EmployeeResponse> call, Response<EmployeeResponse> response) {
                if (response.isSuccessful()) {

                    EmployeeResponse employeeResponse = response.body();

                    Employee employee = employeeResponse.getUser();
                    Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]" + employeeResponse.getToken()+ " name:"+employee.getFirstName());

                    SharedPreferences sharedPreferences = App.getApp().getSharedPreferences(App.getApp().getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    PreferenceUtil.getInstance().setEncStringValue("username", userName);
                    PreferenceUtil.getInstance().setEncStringValue("password", password);
                    PreferenceUtil.getInstance().setEncStringValue("firstName", employee.getFirstName());
                    PreferenceUtil.getInstance().setEncStringValue("lastName", employee.getLastName());
                    PreferenceUtil.getInstance().setEncStringValue("token",  employeeResponse.getToken());
                    editor.commit();
                    result.postValue(employee);
                }

            }

            @Override
            public void onFailure(Call<EmployeeResponse> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                result.postValue(null);
            }
        });

        return result;
    }

//    public LiveData<Employee> register(Employee employee) {
//        MutableLiveData<Employee> result = new MutableLiveData<Employee>();
//
//        Call<Employee> call = ApiHandler.getService().createEmployee(employee);
//
//        call.enqueue(new Callback<Employee>() {
//            @Override
//            public void onResponse(Call<Employee> call, Response<Employee> response) {
//                if (response.isSuccessful()) {
//                    Log.d("test", "onResponse() called with: call = [" + call + "], response = [" + response + "]");
//                    Employee employee = response.body();
//                    result.postValue(employee);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Employee> call, Throwable t) {
//                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
//                result.postValue(employee);
//            }
//        });
//        return result;
//
//    }
}
