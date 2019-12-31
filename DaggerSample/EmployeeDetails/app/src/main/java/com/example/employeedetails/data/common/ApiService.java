package com.example.employeedetails.data.common;

import com.example.employeedetails.data.model.Employee;
import com.example.employeedetails.data.model.Organisation;
import com.example.employeedetails.data.model.Project;
import com.example.employeedetails.data.model.Task;
import com.example.employeedetails.ui.employee.EmployeeResponse;

import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    //Employee API's
    @POST("user/add")
    Call<Employee> createEmployee(@HeaderMap HashMap<String, String> header, @Body Employee employee);

    @POST("user/login")
    @Headers("Content-Type:application/json")
    Call<EmployeeResponse> employeeLogin(@Body RequestBody requestBody);

    @GET("user/")
    Call<List<Employee>> getAllEmployees(@HeaderMap HashMap<String, String> header);

    @GET("user/{id}")
    Call<Employee> getEmployeeById(@HeaderMap HashMap<String, String> header, @Path("id") String id);

    @PUT("user/{id}")
    Call<Employee> updateDetails(@HeaderMap HashMap<String, String> header, @Path("id") String id, @Body Employee employee);

    @DELETE("user/{id}")
    Call<Employee> deleteById(@HeaderMap HashMap<String, String> header, @Path("id") String id);


    //Organisation API's
    @POST("company/add")
    Call<Organisation> addOrganisation(@HeaderMap HashMap<String, String> header, @Body Organisation organisation);

    @GET("company/")
    Call<List<Organisation>> getAllOrgs(@HeaderMap HashMap<String, String> header);

    @GET("company/{id}")
    Call<Organisation> getOrgById(@HeaderMap HashMap<String, String> header, @Path("id") String id);

    @PUT("company/{id}")
    Call<Organisation> updateOrgDetails(@HeaderMap HashMap<String, String> header, @Path("id") String id, @Body Organisation organisation);

    @DELETE("company/{id}")
    Call<Organisation> deleteOrgById(@HeaderMap HashMap<String, String> header, @Path("id") String id);


    //Task API's
    @POST("task/add")
    Call<Task> addTask(@HeaderMap HashMap<String, String> header, @Body Task task);

    @GET("task/")
    Call<List<Task>> getAllTasks(@HeaderMap HashMap<String, String> header);

    @GET("task/{id}")
    Call<Task> getTaskById(@HeaderMap HashMap<String, String> header, @Path("id") String id);

    @PUT("task/{id}")
    Call<Task> updateTaskDetails(@HeaderMap HashMap<String, String> header, @Path("id") String id, @Body Task task);

    @DELETE("task/{id}")
    Call<Task> deleteTaskById(@HeaderMap HashMap<String, String> header, @Path("id") String id);


    //Project API's
    @POST("project/add")
    Call<Project> addProject(@HeaderMap HashMap<String, String> header, @Body Project project);

    @GET("project/")
    Call<List<Project>> getAllProjects(@HeaderMap HashMap<String, String> header);

    @GET("project/{id}")
    Call<Project> getProjectById(@HeaderMap HashMap<String, String> header, @Path("id") String id);

    @PUT("project/{id}")
    Call<Project> updateProjectDetails(@HeaderMap HashMap<String, String> header, @Path("id") String id, @Body Project project);

    @DELETE("project/{id}")
    Call<Project> deleteProjectById(@HeaderMap HashMap<String, String> header, @Path("id") String id);
}
