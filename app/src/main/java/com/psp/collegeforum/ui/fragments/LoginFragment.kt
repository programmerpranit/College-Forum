package com.psp.collegeforum.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.psp.collegeforum.R
import com.psp.collegeforum.databinding.FragmentLoginBinding
import com.psp.collegeforum.ui.viewmodels.AuthViewModels
import com.psp.collegeforum.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewmodel: MainViewModel by activityViewModels()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    private val TAG = "LoginActivity"
    private val RC_SIGN_IN = 11

    private val clientId =
        "855467182800-lb0n7pki7mb37j884nquqlrhgi08s4uu.apps.googleusercontent.com"

    @Inject
    lateinit var jwtKey: String



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "jwt on view created $jwtKey")

        if (jwtKey != "") {
            binding.root.findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(clientId)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        binding.btnLogin.setOnClickListener {
            signIn(mGoogleSignInClient)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val token = account.idToken
            val email = account.email

            if (token != null && email?.endsWith("@acpce.ac.in") == true) {
                lifecycleScope.launch(Dispatchers.Main) {
                    val status = viewmodel.authenticate(token)
                    if (status == 200) {
                        Log.d(TAG, "status 200")
                        binding.root.findNavController()
                            .navigate(R.id.action_loginFragment_to_mainFragment)
                    } else if (status == 201) {
                        Log.d(TAG, "status 201")
                        binding.root.findNavController()
                            .navigate(R.id.action_loginFragment_to_userDetailsFragment)
                        Log.d(TAG, jwtKey)
                    } else {
                        Toast.makeText(requireContext(), "Failed to Login Please Try Again Later", Toast.LENGTH_LONG).show()
                    }
                    Log.d(TAG, status.toString())
                }
            }
            else {
                Toast.makeText(requireContext(), "Login With ACPCE Email" , Toast.LENGTH_SHORT).show()
            }
            Log.d(TAG, token ?: "No token")
//            updateUI(account)
            // Signed in successfully, show authenticated UI.

        } catch (e: ApiException) {
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)

        }
    }

    private fun signIn(client: GoogleSignInClient) {
        val signInIntent = client.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

//    private fun updateUI(account: GoogleSignInAccount?) {
//        if (jwtKey != "" && account != null) {
//            binding.root.findNavController()
//                .navigate(R.id.action_loginFragment_to_userDetailsFragment)
//        }
//    }

//    override fun onStart() {
//        super.onStart()
//
//        if (jwtKey != ""){
//            binding.root.findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
//        }
//
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}





