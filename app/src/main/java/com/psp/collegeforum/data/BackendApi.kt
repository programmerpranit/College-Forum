package com.psp.collegeforum.data

import com.psp.collegeforum.data.models.*
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
        @Field("question_text") question_text: String?,
        @Field("jwttoken") jwtKey: String?
    ): Response<Question>

    @FormUrlEncoded
    @POST("/account/auth")
    suspend fun authenticate(
        @Field("gtoken") gtoken: String?
    ): Response<Jwt>

    @FormUrlEncoded
    @POST("/account/user")
    suspend fun postUser(
        @Field("jwttoken") jwtKey: String?,
        @Field("prn") prn: Int,
        @Field("name") name: String,
        @Field("year_of_study") yos: Int
    ): Response<DetailedUser>

    @FormUrlEncoded
    @POST("/forum/addanswer")
    suspend fun postAnswer(
        @Field("qid") qid: Int,
        @Field("answer_text") answer_text: String,
        @Field("jwttoken") jwtKey: String?

    ): Response<Answer>


}