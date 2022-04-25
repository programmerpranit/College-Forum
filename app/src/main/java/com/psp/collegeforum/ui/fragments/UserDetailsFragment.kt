package com.psp.collegeforum.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.psp.collegeforum.R
import com.psp.collegeforum.databinding.FragmentUserDetailsBinding
import com.psp.collegeforum.ui.viewmodels.AuthViewModels
import com.psp.collegeforum.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.typeOf

@AndroidEntryPoint
class UserDetailsFragment : Fragment(R.layout.fragment_user_details) {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: MainViewModel by activityViewModels()

    @Inject
    lateinit var jwtkey:String

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

        val submitButton = binding.btnSubmitUserDetails

        submitButton.setOnClickListener {
            val username = binding.etUserName.text.toString()
            val prn = binding.etUserPrn.text.toString().toInt()

            val yearOfStudy = binding.etUserYOS.text.toString().toInt()

            Log.d("UserDetailsFrag", jwtkey)


            lifecycleScope.launch(Dispatchers.Main) {
                val key = getJWT()
                Log.d("Key in UDF from get key", key)
                val res = viewmodel.editUser(username, prn, yearOfStudy)

                if (res) {
                    view.findNavController().navigate(R.id.action_userDetailsFragment_to_mainFragment)
                    Toast.makeText(requireContext(), "Account Created Successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Failed to Create an account", Toast.LENGTH_SHORT).show()
                }
            }

        }


    }

    fun getJWT(): String{
        return jwtkey
    }


}