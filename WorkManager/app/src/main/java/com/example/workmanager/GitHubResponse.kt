package com.example.workmanager

/**
Created by sanjana on 30/4/20
 */
data class GitHubResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: ArrayList<Items>
)

data class Items(val id: Long, val name: String, val owner: Owner)

data class Owner(val id: Long, val url: String)
