package com.psp.collegeforum.ui.viewmodels

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psp.collegeforum.data.models.FullQuestion
import com.psp.collegeforum.data.models.Question
import com.psp.collegeforum.data.repositories.MainRepo
import com.psp.collegeforum.util.Constants
import com.psp.collegeforum.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepo
) : ViewModel() {

    @Inject lateinit var jwtkey:String

    @Inject lateinit var sharedPreferences: SharedPreferences


    val TAG = "MainVM"

    private var _questions = MutableLiveData<ArrayList<Question>>()
    val question: LiveData<ArrayList<Question>> = _questions

    private var _searchedQuestions = MutableLiveData<ArrayList<Question>>()
    val searchedQuestion: LiveData<ArrayList<Question>> = _searchedQuestions

    private var _fullquestion = MutableLiveData<FullQuestion>()
    val fullquestion: LiveData<FullQuestion> = _fullquestion

    //Function to fetch questions
    fun getQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            val allQuestions = repository.getAllQuestions()
            if (allQuestions.status == 200) {
                withContext(Dispatchers.Main){
                    _questions.value = allQuestions.data!!
                }
            }
        }
    }

    //Function to fetch full question answers
    fun getFullQuestion(qid: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val fullQuestion = repository.getFullQuestion(qid)
            if (fullQuestion.status == 200) {
                withContext(Dispatchers.Main) {
                    _fullquestion.value = fullQuestion.data!!
                }
            }
        }
    }
    //Function to search questions
    fun searchQuestions(q: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val questions = repository.searchQuestion(q)
            if (questions.status == 200) {
                withContext(Dispatchers.Main) {
                    _searchedQuestions.value = questions.data!!
                }
            }
        }
    }


    //TODO: Optimize following code
    suspend fun postQuestion(question: String): Boolean {

        val key = if(jwtkey != "" && jwtkey != null){
            jwtkey
        } else {
            jwt
        }

        var status = 1000
        val job = viewModelScope.async {
            val req = repository.postQuestion(question, key)
            status = req.status ?: 1000
        }

        job.await()

        Log.d(TAG, (job.isCompleted && status==201).toString())
        return (job.isCompleted && status==201)
    }

    suspend fun postAnswer(qid: Int,answer: String): Boolean {
        var status = 1000

        val key = if(jwtkey != "" && jwtkey != null){
            jwtkey
        } else {
            jwt
        }

        val job = viewModelScope.async {
            val req = repository.postAnswer(qid,answer, key)
            Log.d(TAG, req.message.toString())
            Log.d(TAG, req.status.toString())
            Log.d(TAG, req.data.toString())
            status = req.status ?: 1000
        }
        job.await()
        Log.d(TAG, (job.isCompleted && status == 201).toString())
        return (job.isCompleted && status == 201)

    }

    fun getJWT(): String{
        return jwtkey
    }



    //////////////////



    var jwt = ""


    suspend fun authenticate(gtoken: String): Int {
        var status = 1000
        val job = viewModelScope.async {
            val req = repository.authenticate(gtoken)
            val jwtObj = req.data
            jwt = req.data?.token.toString()
            print(req.data ?: "req data is null")
            Log.d("mainvm", req.data?.token ?: "token")
            if (jwtObj != null) {
                sharedPreferences.edit()
                    .putString(Constants.KEY_JWT, jwtObj.token)
                    .apply()
            }
            if(req.status != null){
                status = req.status
            }
        }
        job.await()

        return status
    }

    suspend fun editUser(name: String, prn: Int, yos:Int):Boolean {
        var status = 1000
        Log.d("JWT KEY", jwtkey ?: "no key")
        Log.d("Edit User", jwt)

        val key = if(jwtkey != "" && jwtkey != null){
            jwtkey
        } else {
            jwt
        }

        val job = viewModelScope.async {
            val req = repository.postUser(name, prn, yos, key)
            status = req.status ?: 1000
        }
        job.await()

        return (job.isCompleted && status==201)
    }


}