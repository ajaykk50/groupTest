package com.example.groupsapp.pojo

import java.io.Serializable

data class ResultX(
    val bio: String,
    val group_photo: String,
    val id: Int,
    val name: String,
    val participant_count: Int,
    val `private`: Boolean,
    val unread_count: Int,
    val user_status: String
):Serializable