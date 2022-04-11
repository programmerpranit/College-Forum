package com.psp.collegeforum.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.psp.collegeforum.R
import com.psp.collegeforum.data.models.Question

class QuestionsAdapter(private val listner:QueClicked) : ListAdapter<Question, QuestionsAdapter.QuestionViewHolder>(DataComparator()) {

//    inner class QuestionViewHolder(
//        private val binding: ItemQuestionBinding
//        ) : RecyclerView.ViewHolder(binding.root)

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title:TextView = itemView.findViewById(R.id.tvQuestionInMRV)
        val name:TextView = itemView.findViewById(R.id.tvUserNameInMRV)
        val time:TextView = itemView.findViewById(R.id.tvTimeInMRV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        val viewHolder = QuestionViewHolder(view)
        view.setOnClickListener{
            listner.onItemClicked(getItem(viewHolder.adapterPosition))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.title.text = currentItem.question_text

//        Log.d("User Obj", user.toString())
//        Log.d("User Name", user.name)

        holder.name.text = currentItem.user.name
        holder.time.text = currentItem.timestamp.slice(0..9)
    }
        class DataComparator : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.qid == newItem.qid
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem == newItem
        }
    }

    interface QueClicked{
        fun onItemClicked(item: Question)
    }
}