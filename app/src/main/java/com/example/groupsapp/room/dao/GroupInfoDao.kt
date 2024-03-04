package com.example.groupsapp.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.groupsapp.room.entity.GroupInfo

@Dao
interface GroupInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroupInfo(groupInfo: GroupInfo)

    @Query("SELECT * from groupinfo")
    suspend fun getAllGroupInfo(): List<GroupInfo>

    @Query("SELECT * FROM groupinfo WHERE  group_id = :groupId")
    suspend fun getGroupInfo(
        groupId: String
    ): GroupInfo

    @Query("UPDATE groupinfo SET group_name = :name,group_about = :about WHERE group_id = :groupId")
    suspend fun updateGroupInfo(name: String, about: String, groupId: String)

}