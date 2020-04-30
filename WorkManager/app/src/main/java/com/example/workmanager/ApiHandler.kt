package com.example.workmanager

import com.squareup.picasso.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
Created by sanjana on 30/4/20
 */
object ApiHandler {
    fun getService(): GitHubService {
        val client: OkHttpClient
        client = if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        } else {
            OkHttpClient.Builder()
                .build()
        }
        val retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.github.com")
            .build()
        return retrofit.create(GitHubService::class.java)
    }
}