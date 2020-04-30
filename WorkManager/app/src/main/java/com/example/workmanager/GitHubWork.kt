package com.example.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
Created by sanjana on 30/4/20
 */
class GitHubWork(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        getData()
        return Result.success()
    }

    private fun getData() {
        val call = ApiHandler.getService().listRepo("rmkrishna")

        call!!.enqueue(object : Callback<GitHubResponse?> {
            override fun onResponse(
                call: Call<GitHubResponse?>,
                response: Response<GitHubResponse?>
            ) {

                if (response.isSuccessful) {
                    Log.d("M", "onResponse: ")
                    val gitHubResponse = response.body()
                    val items: ArrayList<Items> = gitHubResponse!!.items

                    items.indices.forEach { i ->
                        print("The url is ${items.get(i).owner.url}")
                    }
                }
            }

            override fun onFailure(
                call: Call<GitHubResponse?>,
                t: Throwable
            ) {
                Log.d(
                    "MM",
                    "onFailure() called with: call = [$call], t = [$t]"
                )
            }
        })
    }


}