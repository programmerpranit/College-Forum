package com.psp.collegeforum.data

import com.psp.collegeforum.data.models.Answer
import com.psp.collegeforum.data.models.FullQuestion
import com.psp.collegeforum.data.models.Question
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface BackendApi {

    @GET("/forum/questions")
    suspend fun getQuestions(): Response<ArrayList<Question>>


    @GET("/forum/fullquestion")
    suspend fun getFullQuestion(@Query("qid") qid: Int): Response<FullQuestion>

    @FormUrlEncoded
    @POST("/forum/addquestion")
    suspend fun postQuestion(
        @Field("question_text") question_text: String?
    ): Response<Question>



}