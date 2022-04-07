package com.psp.collegeforum.data.models

data class Question(
    val qid: Int,
    val question_text: String,
    val timestamp: String,
    val user: User
)