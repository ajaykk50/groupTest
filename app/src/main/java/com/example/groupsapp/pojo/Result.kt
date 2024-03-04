package com.example.groupsapp.pojo

data class Result(
    val current_page: Int,
    val groups: List<Group>,
    val next_page: Boolean,
    val total: Int
)