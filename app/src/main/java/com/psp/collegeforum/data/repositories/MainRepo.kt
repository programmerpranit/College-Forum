package com.psp.collegeforum.data.repositories

import android.util.Log
import com.psp.collegeforum.data.BackendApi
import com.psp.collegeforum.data.models.*
import com.psp.collegeforum.util.Resource
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Named

class MainRepo @Inject constructor(
    private val api: BackendApi
) {

    val TAG = "REPO"

//    val jwtkey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTU2NTA5OTk3NDI3MzA3NDQyMTgiLCJleHAiOjE2NTE4NTk1OTIsImlhdCI6MTY0OTI2NzU5Mn0.MmCqNZJ18nR74xQK4Cu-T4iw0dESW4x6ZnkGIlOrvkc"

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

    suspend fun searchQuestion(q:String): Resource<ArrayList<Question>> {
        return try {
            val res = api.searchQuestion(q)

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

    suspend fun postQuestion(questionText: String, jwtkey:String): Resource<Question> {

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

    suspend fun authenticate(gToken: String): Resource<Jwt> {
        return try {
            val res = api.authenticate(gToken)
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

    suspend fun postUser(name: String, prn: Int, yos: Int, jwtkey: String): Resource<DetailedUser> {
        return try {
            val res = api.postUser(jwtkey, prn, name, yos)
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

    suspend fun postAnswer(qid : Int,answerText : String, jwtkey: String): Resource<Answer>{
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