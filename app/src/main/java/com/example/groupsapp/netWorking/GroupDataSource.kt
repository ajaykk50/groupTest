package com.example.groupsapp.netWorking

import com.example.groupsapp.pojo.groupDetails
import com.example.groupsapp.pojo.groupList
import retrofit2.Response

interface GroupDataSource {
    suspend fun getGroupList(): Response<groupList>
    suspend fun getGroupDetails(id:String): Response<groupDetails>
}