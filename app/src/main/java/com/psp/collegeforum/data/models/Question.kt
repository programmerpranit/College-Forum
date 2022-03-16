package com.psp.collegeforum.data.models

data class Question(
    val completed: Boolean,
    var id: Int,
    val title: String,
    val userId: Int
)