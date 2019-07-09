package com.example.employeedetails;

import com.example.employeedetails.data.response.EmployeeResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface EmployeeService {

    @POST("user/register")
    Call<EmployeeResponse> createEmployee(@Body EmployeeResponse employeeResponse);

    @POST("user/login")
    @Headers("Content-Type:application/json")
    Call<EmployeeResponse> employeeLogin(@Body RequestBody body);
}
