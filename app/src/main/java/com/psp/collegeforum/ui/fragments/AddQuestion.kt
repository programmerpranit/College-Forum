package com.psp.collegeforum.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.psp.collegeforum.R
import com.psp.collegeforum.databinding.FragmentAddQuestionBinding
import com.psp.collegeforum.databinding.FragmentQuestionBinding
import com.psp.collegeforum.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddQuestion : Fragment(R.layout.fragment_add_question) {

    private val viewmodel: MainViewModel by activityViewModels()
    lateinit var binding: FragmentAddQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val submitButton = binding.btnAddQuestion

        submitButton.setOnClickListener {

            val question = binding.etAddQuestion.text.toString()

            if (question != null) {
                lifecycleScope.launch(Dispatchers.Main) {
                    val res = viewmodel.postQuestion(question)

                    if (res) {
                        view.findNavController().navigate(R.id.action_addQuestion_to_mainFragment)
                        Toast.makeText(requireContext(),
                            "Question Added Successfully",
                            Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(),
                            "Failed to add question",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(requireContext(),"Enter a valid Question",Toast.LENGTH_LONG).show()
            }
        }
    }

}