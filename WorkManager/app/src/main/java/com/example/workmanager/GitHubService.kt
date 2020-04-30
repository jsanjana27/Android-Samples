package com.example.workmanager

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
Created by sanjana on 30/4/20
 */
interface GitHubService {
    @GET("/search/repositories")
    fun listRepo(@Query("q") user: String?): Call<GitHubResponse?>?
}