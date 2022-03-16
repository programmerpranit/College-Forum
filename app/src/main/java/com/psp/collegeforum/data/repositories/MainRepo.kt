package com.psp.collegeforum.data.repositories

import com.psp.collegeforum.data.BackendApi
import com.psp.collegeforum.data.models.Question
import javax.inject.Inject

class MainRepo @Inject constructor(
    private val api: BackendApi
) {

    suspend fun getAllQuestions(): ArrayList<Question>{
        return api.getQuestions()
    }


}