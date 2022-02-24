package com.psp.collegeforum.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.psp.collegeforum.R
import kotlinx.android.synthetic.main.fragment_login.view.*


class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login,container,false)

        view.btnLogin.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_userDetailsFragment)
        }
        return view
    }
    }
