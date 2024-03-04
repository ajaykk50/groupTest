package com.example.groupsapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.groupsapp.room.dao.GroupInfoDao
import com.example.groupsapp.room.entity.GroupInfo

@Database(entities = [GroupInfo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun groupInfoDao(): GroupInfoDao
}
