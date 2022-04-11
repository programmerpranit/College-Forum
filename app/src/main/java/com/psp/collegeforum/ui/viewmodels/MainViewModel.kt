package com.psp.collegeforum.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psp.collegeforum.data.models.Answer
import com.psp.collegeforum.data.models.FullQuestion
import com.psp.collegeforum.data.models.Question
import com.psp.collegeforum.data.repositories.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepo
) : ViewModel() {

    private var _questions = MutableLiveData<ArrayList<Question>>()
    val question: LiveData<ArrayList<Question>> = _questions

    private var _fullquestion = MutableLiveData<FullQuestion>()
    val fullquestion: LiveData<FullQuestion> = _fullquestion

    //Function to fetch questions
    fun getQuestions() {
        viewModelScope.launch {
            val allQuestions = repository.getAllQuestions()
            if (allQuestions.status == 200){
                _questions.value = allQuestions.data!!
            }
        }
    }

    //Function to fetch full question answers
    fun getFullQuestion(qid:Int) {
        viewModelScope.launch {
            val fullQuestion = repository.getFullQuestion(qid)
            if (fullQuestion.status == 200){
                _fullquestion.value = fullQuestion.data!!
            }
        }
    }


    fun postQuestion(){

    }

}