package com.psp.collegeforum.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.psp.collegeforum.data.repositories.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepo
) : ViewModel() {

}