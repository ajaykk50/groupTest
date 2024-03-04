package com.example.groupsapp.apis

import com.example.groupsapp.pojo.groupDetails
import com.example.groupsapp.pojo.groupList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GroupService {

    @GET("podium_dummy")
    suspend fun fetchGroups(): Response<groupList>

    @GET("podium_dummy/{id}")
    suspend fun fetchGroupDetails(@Path("id") id: String): Response<groupDetails>
}