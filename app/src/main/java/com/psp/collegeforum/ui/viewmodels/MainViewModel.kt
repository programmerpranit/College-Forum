package com.psp.collegeforum.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psp.collegeforum.data.models.Question
import com.psp.collegeforum.data.repositories.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepo
) : ViewModel() {

    private var _questions = MutableLiveData<ArrayList<Question>>()
    val question: LiveData<ArrayList<Question>> = _questions

    fun getQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            _questions.value = repository.getAllQuestions()
        }
    }


}