package com.psp.collegeforum.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.psp.collegeforum.R
import com.psp.collegeforum.core.MainActivity
import com.psp.collegeforum.databinding.FragmentLoginBinding
import com.psp.collegeforum.ui.viewmodels.AuthViewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.view.*
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewmodel: AuthViewModels by activityViewModels()

//    @Inject
//    private lateinit var jwtKey:String

    val jwtKey = ""

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    private val TAG = "LoginActivity"
    private val RC_SIGN_IN = 11

//    private val clientId =
//        "855467182800-lb0n7pki7mb37j884nquqlrhgi08s4uu.apps.googleusercontent.com"


    private val clientId = "855467182800-rr0bmt0cjd1i2cf1chq0ohc0ts7h1k9v.apps.googleusercontent.com"
//
//    client secerate = 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



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

        if(jwtKey != ""){
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_mainFragment)
        }
        

        view.btnLogin.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_mainFragment)
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(clientId)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        _binding?.btnLogin?.setOnClickListener {
            signIn(mGoogleSignInClient)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val token = account.idToken
            Log.d(TAG, token ?: "No token")
            println(account.account)
            updateUI(account)
            // Signed in successfully, show authenticated UI.

        } catch (e: ApiException) {
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }
    }

    private fun signIn(client: GoogleSignInClient) {
        val signInIntent = client.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onStart() {
        super.onStart()

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        val account = GoogleSignIn.getLastSignedInAccount(requireContext())
        updateUI(account)

    }

    private fun updateUI(account: GoogleSignInAccount?) {
        if (account != null) {
            Log.d("LOGIN","Login completed")
            Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_mainFragment)
            // p -- Send request to backend to check if email is valid
            // create a user if there is none and return the user data

//            Log.d(TAG, "UI Updated")
//            val mIntent = Intent(this, MainActivity::class.java)
//            mIntent.putExtra("email", account.email)
//            startActivity(mIntent)
//            finish()

        }
        Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_mainFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}





