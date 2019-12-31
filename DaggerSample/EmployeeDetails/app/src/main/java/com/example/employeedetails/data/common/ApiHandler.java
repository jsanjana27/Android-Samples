package com.example.employeedetails.data.common;

import com.example.employeedetails.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHandler {

    public static ApiService getService() {

        OkHttpClient client;
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();
        } else {
            client = new OkHttpClient.Builder()
                    .build();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://172.17.24.157:8080/api/")
                .build();

        return retrofit.create(ApiService.class);
    }
}
