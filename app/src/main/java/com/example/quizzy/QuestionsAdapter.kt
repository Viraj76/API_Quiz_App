package com.example.quizzy

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.quizzy.databinding.ItemViewQuestionsBinding
import com.example.quizzy.models.Result

class QuestionsAdapter(
    val onShowAnswerClick: (Result, List<TextView>) -> Unit,
    val context: Context
) : RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder>() {
    class QuestionsViewHolder(val binding : ItemViewQuestionsBinding) : ViewHolder(binding.root)

    val diffUtil = object  : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.correct_answer == newItem.correct_answer
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,diffUtil)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
        return QuestionsViewHolder(ItemViewQuestionsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: QuestionsViewHolder, position: Int) {
        val question = differ.currentList[position]
        holder.binding.apply {
            val number = position + 1
            tvQno.text = number.toString() + ". "
            val correct = question.correct_answer
            tvQuestions.text = question.question

            val tvs = listOf<TextView>(tvOption1,tvOption2,tvOption3,tvOption4)

            val options = listOf(question.incorrect_answers[0] , question.incorrect_answers[1] , question.incorrect_answers[2] , question.correct_answer)

            val randomNumbers = listOf(0, 1, 2, 3).shuffled().take(4)
            tvOption1.text = options[randomNumbers[0]]
            tvOption2.text =options[randomNumbers[1]]
            tvOption3.text = options[randomNumbers[2]]
            tvOption4.text = options[randomNumbers[3]]

//            tvOption1.setOnClickListener {
//                if(correct == tvOption1.text){
//                    tvOption1.backgroundTintList = ContextCompat.getColorStateList(context,R.color.green)
//                }
//                else{
//                    tvOption1.backgroundTintList = ContextCompat.getColorStateList(context,R.color.red)
//                }
//            }
//            tvOption2.setOnClickListener {
//                if(correct == tvOption2.text){
//                    tvOption2.backgroundTintList = ContextCompat.getColorStateList(context,R.color.green)
//                }
//                else{
//                    tvOption2.backgroundTintList = ContextCompat.getColorStateList(context,R.color.red)
//                }
//            }
//            tvOption3.setOnClickListener {
//                if(correct == tvOption3.text){
//                    tvOption3.backgroundTintList = ContextCompat.getColorStateList(context,R.color.green)
//                }
//                else{
//                    tvOption3.backgroundTintList = ContextCompat.getColorStateList(context,R.color.red)
//                }
//            }
            tvOption4.setOnClickListener {
                if(correct == tvOption4.text){
                    tvOption4.backgroundTintList = ContextCompat.getColorStateList(context,R.color.green)
                }
                else{
                    tvOption4.backgroundTintList = ContextCompat.getColorStateList(context,R.color.red)
                }
            }

            btnShowAnswer.setOnClickListener {
//                Log.d ("tt" , randomNumbers.toString())
                onShowAnswerClick(question , tvs)
            }
//

        }


    }


}