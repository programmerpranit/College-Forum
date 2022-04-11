package com.psp.collegeforum.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.psp.collegeforum.R
import com.psp.collegeforum.databinding.FragmentLoginBinding
import com.psp.collegeforum.databinding.FragmentUserDetailsBinding
import com.psp.collegeforum.ui.viewmodels.AuthViewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : Fragment(R.layout.fragment_user_details) {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: AuthViewModels by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = binding.etUserName.text.toString()
        val prn = binding.etUserPrn.text.toString().toInt()
        val yearOfStudy = binding.etUserYOS.text.toString().toInt()






    }


}