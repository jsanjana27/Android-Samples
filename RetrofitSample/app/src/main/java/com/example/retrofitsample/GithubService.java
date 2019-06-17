package com.example.retrofitsample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubService {

    @GET("/search/repositories")
    Call<GitHubResponse> listRepo(@Query("q") String user);
}
