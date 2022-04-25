package com.psp.collegeforum.data.models

data class Answer(
    val aid: Int,
    val answer_text: String,
    val dislikes: Int,
    val likes: Int,
    val timestamp: String,
    val user: User
)