package com.psp.collegeforum.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.psp.collegeforum.R
import com.psp.collegeforum.data.models.Question

class QuestionSearchAdapter() : ListAdapter<Question, QuestionSearchAdapter.QuestionAnsViewHolder>(DataComparator()) {

//    inner class QuestionViewHolder(
//        private val binding: ItemQuestionBinding
//        ) : RecyclerView.ViewHolder(binding.root)

    inner class QuestionAnsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title:TextView = itemView?.findViewById(R.id.tvQuestionOnSearch)
        val ans:TextView = itemView?.findViewById(R.id.tvAnswerOnSearch)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionSearchAdapter.QuestionAnsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_question, parent, false)
        return QuestionAnsViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionSearchAdapter.QuestionAnsViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.title.text = currentItem.title
        holder.ans.text = currentItem.title
    }

    class DataComparator : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem == newItem
        }
    }
}