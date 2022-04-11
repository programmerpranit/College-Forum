package com.psp.collegeforum.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.psp.collegeforum.R
import com.psp.collegeforum.databinding.FragmentQuestionBinding
import com.psp.collegeforum.ui.adapters.AnswerAdapter
import com.psp.collegeforum.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class QuestionFragment : Fragment() {

    private val viewmodel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentQuestionBinding
    private lateinit var adapter: AnswerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val jwtkey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTU2NTA5OTk3NDI3MzA3NDQyMTgiLCJleHAiOjE2NTE4NTk1OTIsImlhdCI6MTY0OTI2NzU5Mn0.MmCqNZJ18nR74xQK4Cu-T4iw0dESW4x6ZnkGIlOrvkc"
        val qid = arguments?.getString("qid").toString().toInt()
        viewmodel.getFullQuestion(qid)

        adapter = AnswerAdapter()
        val addAnswer = binding.btnAddAnswer
        addAnswer.setOnClickListener {
            val answer = binding.etAddAnswer.text.toString()

            lifecycleScope.launch(Dispatchers.Main) {
                val res = viewmodel.postAnswer(qid,answer)
                if (res){
                    Toast.makeText(requireContext(), "Answer Added Successfully", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(), "Answer Not", Toast.LENGTH_SHORT).show()
                }
                viewmodel.getFullQuestion(qid)
            }

        }

        viewmodel.fullquestion.observe(viewLifecycleOwner) { fullQuestion ->
            adapter.submitList(fullQuestion.answers)
            binding.tvQuestionInQuestionFrag.text = fullQuestion.question.question_text
        }

        val recyclerView = binding.rvInQuestionFrag
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }





}