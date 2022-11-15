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
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.psp.collegeforum.R
import com.psp.collegeforum.data.models.Question
import com.psp.collegeforum.databinding.FragmentSearchBinding
import com.psp.collegeforum.ui.adapters.QuestionsAdapter
import com.psp.collegeforum.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), QuestionsAdapter.QueClicked {

    private lateinit var binding: FragmentSearchBinding
    private val viewmodel: MainViewModel by activityViewModels()

    private lateinit var adapter: QuestionsAdapter

    val TAG = "SearchFrag"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = QuestionsAdapter(this)

        val recyclerView = binding.rvInSearchFrag
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        recyclerView.adapter = adapter


        binding.btnSearch.setOnClickListener {
            val searchQuery = binding.etQuestionInSearchFrag.text.toString()

            if (searchQuery.isNotEmpty() && searchQuery.isNotBlank()) {
                viewmodel.searchQuestions(searchQuery)

            } else {
                Toast.makeText(requireContext(), "Search Query Can't Be Blank", Toast.LENGTH_SHORT).show()
            }

        }

        viewmodel.searchedQuestion.observe(viewLifecycleOwner) { list ->

            adapter.submitList(list)
        }


    }

    override fun onItemClicked(item: Question) {
        val bundle = bundleOf("qid" to item.qid.toString())
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_searchFragment_to_questionFragment, bundle)
    }
}