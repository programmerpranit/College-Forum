package com.psp.collegeforum.ui.viewmodels

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psp.collegeforum.data.repositories.MainRepo
import com.psp.collegeforum.util.Constants.KEY_JWT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class AuthViewModels @Inject constructor(
    private val repository: MainRepo
) : ViewModel(){


    @Inject lateinit var sharedPreferences: SharedPreferences

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
                    .putString(KEY_JWT, jwtObj.token)
                    .apply()
            }
            if(req.status != null){
                status = req.status
            }
        }
        job.await()

        return status
    }

    suspend fun editUser(name: String, prn: Int, yos:Int, jwtkey:String):Boolean {
        var status = 1000
        Log.d("JWT KEY", jwtkey)
        Log.d("Edit User", jwt)
        val job = viewModelScope.async {
            val req = repository.postUser(name, prn, yos, jwt)
            status = req.status ?: 1000
        }
        job.await()

        return (job.isCompleted && status==201)
    }



}