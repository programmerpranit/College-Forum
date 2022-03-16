package com.psp.collegeforum.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.psp.collegeforum.R
import com.psp.collegeforum.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddQuestion : Fragment(R.layout.fragment_add_question) {

    private val viewmodel: MainViewModel by activityViewModels()

}