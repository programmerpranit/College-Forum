package com.psp.collegeforum.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.psp.collegeforum.R
import com.psp.collegeforum.data.models.Answer
import com.psp.collegeforum.data.models.Question
import com.psp.collegeforum.data.models.FullQuestion

class AnswerAdapter : ListAdapter<Answer, AnswerAdapter.AnswerViewHolder>(DataComparator()) {

//    inner class QuestionViewHolder(
//        private val binding: ItemQuestionBinding
//        ) : RecyclerView.ViewHolder(binding.root)

    inner class AnswerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val anstitle:TextView = itemView.findViewById(R.id.tvAnswerInQuestionFrag)
//        val ansupvote:TextView= itemView.findViewById(R.id.tvUpVote)
//        val ansdownvote:TextView= itemView.findViewById(R.id.tvDownVote)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_answers, parent, false)
        val viewHolder = AnswerViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.anstitle.text = currentItem.answer_text
//        holder.ansupvote.text = currentItem.likes.toString()
//        holder.ansdownvote.text = currentItem.dislikes.toString()
    }

    class DataComparator : DiffUtil.ItemCallback<Answer>() {
        override fun areItemsTheSame(oldItem: Answer, newItem: Answer): Boolean {
            return oldItem.aid == newItem.aid
        }

        override fun areContentsTheSame(oldItem: Answer, newItem: Answer): Boolean {
            return oldItem == newItem
        }
    }
}