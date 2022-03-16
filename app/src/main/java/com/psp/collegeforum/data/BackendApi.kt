package com.psp.collegeforum.data

import com.psp.collegeforum.data.models.Question
import retrofit2.Response
import retrofit2.http.GET

interface BackendApi {
    //
//    @GET("/auth")
//    suspend fun authUser(
//        @Field("idToken") idToken: String
//    ) : Response<DataClass>
    @GET("/todos")
    suspend fun getQuestions(): ArrayList<Question>


}