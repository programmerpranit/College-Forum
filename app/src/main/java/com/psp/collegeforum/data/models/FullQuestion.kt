package com.psp.collegeforum.data.models

data class FullQuestion(
    val answers: ArrayList<Answer>,
    val question: Question
)