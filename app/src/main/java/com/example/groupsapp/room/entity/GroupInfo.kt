package com.example.groupsapp.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groupinfo")
data class GroupInfo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val group_id: String,
    val group_name: String,
    val group_about: String
)