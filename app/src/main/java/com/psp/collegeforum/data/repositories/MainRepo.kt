package com.psp.collegeforum.data.repositories

import com.psp.collegeforum.data.BackendApi
import com.psp.collegeforum.data.models.Answer
import com.psp.collegeforum.data.models.FullQuestion
import com.psp.collegeforum.data.models.Question
import retrofit2.Call
import javax.inject.Inject

class MainRepo @Inject constructor(
    private val api: BackendApi
) {

    suspend fun getAllQuestions(): ArrayList<Question>{
        return api.getQuestions()
    }

    suspend fun getFullQuestion(): FullQuestion {
        return api.getFullQuestion()
    }

    suspend fun postQuestion(): String {
        return api.postQuestion("").toString()
    }

}