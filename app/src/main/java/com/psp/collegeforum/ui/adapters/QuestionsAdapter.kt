package com.psp.collegeforum.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.psp.collegeforum.R
import com.psp.collegeforum.data.models.Question

class QuestionsAdapter : ListAdapter<Question, QuestionsAdapter.QuestionViewHolder>(DataComparator()) {

//    inner class QuestionViewHolder(
//        private val binding: ItemQuestionBinding
//        ) : RecyclerView.ViewHolder(binding.root)

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title:TextView = itemView.findViewById(R.id.tvAnswerInMRV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.title.text = currentItem.title
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