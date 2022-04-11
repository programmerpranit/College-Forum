package com.psp.collegeforum.data.repositories

import com.psp.collegeforum.data.BackendApi
import com.psp.collegeforum.data.models.Answer
import com.psp.collegeforum.data.models.FullQuestion
import com.psp.collegeforum.data.models.Question
import com.psp.collegeforum.util.Resource
import retrofit2.Call
import javax.inject.Inject

class MainRepo @Inject constructor(
    private val api: BackendApi
) {

    suspend fun getAllQuestions(): Resource<ArrayList<Question>>{
        return try {
            val res = api.getQuestions()
            val result = res.body()
            val status = res.code()
            if(res.isSuccessful && result != null) {
                Resource.Success(result, status)
            } else {
                Resource.Error(res.message())
            }

        } catch (e: Exception){
            Resource.Error(e.localizedMessage ?: "Unknown Error occurred")
        }


    }

    suspend fun getFullQuestion(qid:Int): Resource<FullQuestion> {
        return try {
            val res = api.getFullQuestion(qid)
            val result = res.body()
            val status = res.code()
            if(res.isSuccessful && result != null) {
                Resource.Success(result, status)
            } else {
                Resource.Error(res.message())
            }

        } catch (e: Exception){
            Resource.Error(e.localizedMessage ?: "Unknown Error occurred")
        }
    }

    suspend fun postQuestion(): String {
        return api.postQuestion("").toString()
    }

}