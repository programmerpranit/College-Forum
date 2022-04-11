package com.psp.collegeforum.data

import com.psp.collegeforum.data.models.Answer
import com.psp.collegeforum.data.models.FullQuestion
import com.psp.collegeforum.data.models.Question
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface BackendApi {

    @GET("/forum/questions")
    suspend fun getQuestions(): ArrayList<Question>


    @GET("/forum/fullquestion")
    suspend fun getFullQuestion(): ArrayList<Answer>


}