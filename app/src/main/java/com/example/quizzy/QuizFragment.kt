package com.example.quizzy

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.quizzy.api.ApiUtilities
import com.example.quizzy.databinding.FragmentQuizBinding
import com.example.quizzy.models.Questions
import com.example.quizzy.models.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class QuizFragment : Fragment() {
    private lateinit var binding : FragmentQuizBinding
    private lateinit var adapter: QuestionsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(layoutInflater)
        setStatusBarColor()
        getTheQuestions()
        return binding.root
    }

    private fun getTheQuestions() {
        Utils.showDialog(requireContext(),"Generating Questions")
        val bundle = arguments
        val category = bundle?.getInt("category")
        val difficulty = bundle?.getString("difficulty")
        val type = bundle?.getString("type")
        val numberOfQ = bundle?.getInt("numberOfQ")

        ApiUtilities.api.getAllQuestions(numberOfQ!!
            ,category!!,difficulty!!,type!!).enqueue(object :
            Callback<Questions> {
            override fun onResponse(call: Call<Questions>, response: Response<Questions>) {
                if (response.isSuccessful){
                    val questions = response.body()?.results

                    questions?.let { questionList ->
                        for ((index, result) in questionList.withIndex()) {
                                    Log.d("tt", "Index: $index, Result: $result")
                            adapter = QuestionsAdapter(::onShowAnswerClick,requireContext())
                            binding.rvQuestions.adapter = adapter
                            Utils.hideDialog()
                            adapter.differ.submitList(questionList)
                        }
                    }
                }
                else{
                    Log.d("tt", "none")
                }
            }

            override fun onFailure(call: Call<Questions>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun onShowAnswerClick(questions : Result , tvs : List<TextView>){
        val rightAnswer = questions.correct_answer

        if(tvs[0].text == rightAnswer){
            tvs[0].backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.green)
        }
//        else{
//            tvs[1].backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.red)
//            tvs[2].backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.red)
//            tvs[3].backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.red)
//        }

        if(tvs[1].text == rightAnswer){
            tvs[1].backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.green)
        }
//        else{
//            tvs[0].backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.red)
//            tvs[2].backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.red)
//            tvs[3].backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.red)
//        }

        if(tvs[2].text == rightAnswer){
            tvs[2].backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.green)
        }
//        else{
//            tvs[1].backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.red)
//            tvs[0].backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.red)
//            tvs[3].backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.red)
//        }

        if(tvs[3].text == rightAnswer){
            tvs[3].backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.green)
        }
//        else{
//            tvs[1].backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.red)
//            tvs[2].backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.red)
//            tvs[0].backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.red)
//        }
    }

    private fun setStatusBarColor() {
        activity?.window?.apply {
            val statusBarColors = ContextCompat.getColor(requireContext(), R.color.blue1)
            statusBarColor = statusBarColors
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }

}


