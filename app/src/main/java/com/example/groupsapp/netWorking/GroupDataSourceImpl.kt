package com.example.groupsapp.netWorking

import com.example.groupsapp.apis.GroupService
import com.example.groupsapp.pojo.groupDetails
import com.example.groupsapp.pojo.groupList
import retrofit2.Response
import javax.inject.Inject

class GroupDataSourceImpl @Inject constructor(private val groupService: GroupService) :
    GroupDataSource {
    override suspend fun getGroupList(): Response<groupList> = groupService.fetchGroups()
    override suspend fun getGroupDetails(id:String): Response<groupDetails> = groupService.fetchGroupDetails(id)
}