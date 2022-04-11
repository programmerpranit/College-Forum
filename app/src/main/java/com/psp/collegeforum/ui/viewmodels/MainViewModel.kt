package com.psp.collegeforum.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psp.collegeforum.data.models.FullQuestion
import com.psp.collegeforum.data.models.Question
import com.psp.collegeforum.data.repositories.MainRepo
import com.psp.collegeforum.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepo
) : ViewModel() {

    val TAG = "MainVM"

    private var _questions = MutableLiveData<ArrayList<Question>>()
    val question: LiveData<ArrayList<Question>> = _questions

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


    //TODO: Optimize following code
    suspend fun postQuestion(question: String): Boolean {
        var status = 1000
        val job = viewModelScope.async {
            val req = repository.postQuestion(question)
            Log.d(TAG, req.message.toString())
            Log.d(TAG, req.status.toString())
            Log.d(TAG, req.data.toString())
            status = req.status ?: 1000
        }

        job.await()

        Log.d(TAG, (job.isCompleted && status==201).toString())
        return (job.isCompleted && status==201)
    }
//    fun postQuestion(question: String): Boolean {
//        var response = false
//        viewModelScope.launch(Dispatchers.IO) {
//            val fullQuestion = repository.postQuestion(question)
//            response = (fullQuestion.status == 201)
//        }
//        return response
//    }

}