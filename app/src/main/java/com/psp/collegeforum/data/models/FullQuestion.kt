package com.psp.collegeforum.data.models

data class FullQuestion(
    val answers: List<Answer>,
    val question: Question
)