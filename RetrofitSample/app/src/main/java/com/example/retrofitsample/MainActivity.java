package com.example.retrofitsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Call<GitHubResponse> call = ApiHandler.getService().listRepo("rmkrishna");

        call.enqueue(new Callback<GitHubResponse>() {
            @Override
            public void onResponse(Call<GitHubResponse> call, Response<GitHubResponse> response) {

                Log.d("MM", "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                if (response.isSuccessful()) {
                    Log.d("M", "onResponse: ");
                    GitHubResponse gitHubResponse = response.body();

                    ArrayList<Items> items = gitHubResponse.getItems();

                    if (gitHubResponse == null) {
                        Log.d("MM", "onResponse: is null");
                    }

                    Log.d("MM", "onResponse: " + gitHubResponse.getTotal_count());
                    Log.d("MM", "onResponse: " + gitHubResponse.isIncomplete_results());
//
                    for (int i = 0; i < items.size(); i++) {
                        Log.d("Items", "Id: " + items.get(i).getId() + " Name: "+ items.get(i).getName() + "  Owner: "+items.get(i).getOwner().getUrl());
                    }
                }
            }

            @Override
            public void onFailure(Call<GitHubResponse> call, Throwable t) {
                Log.d("MM", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
    }
}
