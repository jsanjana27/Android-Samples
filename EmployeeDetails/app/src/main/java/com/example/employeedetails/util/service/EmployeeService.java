package com.example.employeedetails.util.service;

import com.example.employeedetails.data.response.Employee;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EmployeeService {

    @POST("user/register")
    Call<Employee> createEmployee(@Body Employee employee);

    @POST("user/login")
    @Headers("Content-Type:application/json")
    Call<Employee> employeeLogin(@Body RequestBody requestBody);

    @GET("user/:id?")
    @Headers("authorization:")
    Call<Employee> getEmployee(@HeaderMap HashMap<String, String> header, @Query("id=") long id);
}
