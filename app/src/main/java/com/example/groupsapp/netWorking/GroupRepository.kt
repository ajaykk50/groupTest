package com.example.groupsapp.netWorking

import com.example.groupsapp.pojo.groupDetails
import com.example.groupsapp.pojo.groupList
import com.example.groupsapp.room.dao.GroupInfoDao
import com.example.groupsapp.room.entity.GroupInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class GroupRepository @Inject constructor(
    private val groupDataSource: GroupDataSource,
    private val groupInfoDao: GroupInfoDao
) {
    fun getGroupList(): Flow<ResourceState<groupList>> {
        return object : NetworkBoundSource<groupList, groupList>() {
            override suspend fun fetchFromRemote(): Response<groupList> {
                return groupDataSource.getGroupList()
            }

            override suspend fun postProcess(originalData: groupList): groupList {
                return originalData
            }

        }.asFlow().flowOn(Dispatchers.IO)
    }

    fun getGroupDetails(id: String): Flow<ResourceState<groupDetails>> {
        return object : NetworkBoundSource<groupDetails, groupDetails>() {
            override suspend fun fetchFromRemote(): Response<groupDetails> {
                return groupDataSource.getGroupDetails(id)
            }

            override suspend fun postProcess(originalData: groupDetails): groupDetails {
                return originalData
            }

        }.asFlow().flowOn(Dispatchers.IO)
    }

    suspend fun editUpdateGroupInfo(groupInfo: GroupInfo) {
        groupInfoDao.insertGroupInfo(groupInfo)
    }

    suspend fun getGroupInfo(groupId: String): GroupInfo {
        return groupInfoDao.getGroupInfo(groupId)
    }

    suspend fun updateGroupInfo(name: String, bio: String, groupId: String) {
        return groupInfoDao.updateGroupInfo(name, bio, groupId)
    }

}