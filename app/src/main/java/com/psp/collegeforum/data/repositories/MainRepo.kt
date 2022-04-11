package com.psp.collegeforum.data.repositories

import com.psp.collegeforum.data.BackendApi
import com.psp.collegeforum.data.models.Answer
import com.psp.collegeforum.data.models.FullQuestion
import com.psp.collegeforum.data.models.Question
import com.psp.collegeforum.util.Resource
import javax.inject.Inject

class MainRepo @Inject constructor(
    private val api: BackendApi
) {
//    @Inject
//    @Named("jwtkey")
//    private lateinit var jwtkey:String

    val jwtkey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTU2NTA5OTk3NDI3MzA3NDQyMTgiLCJleHAiOjE2NTE4NTk1OTIsImlhdCI6MTY0OTI2NzU5Mn0.MmCqNZJ18nR74xQK4Cu-T4iw0dESW4x6ZnkGIlOrvkc"

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

    suspend fun postQuestion(questionText: String): Resource<Question> {

        return try {
            val res = api.postQuestion(questionText, jwtkey)
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

    suspend fun postAnswer(qid : Int,answerText : String): Resource<Answer>{
        return try {
            val res = api.postAnswer(qid,answerText, jwtkey)
            val result = res.body()
            val status = res.code()
            if(res.isSuccessful && result != null){
                Resource.Success(result,status)
            }else{
                Resource.Error(res.message())
            }
        } catch (e: Exception){
            Resource.Error(e.localizedMessage ?: "Unknown Error Occurred")
        }

    }

}