package com.example.quizzy

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.quizzy.api.ApiUtilities
import com.example.quizzy.databinding.FragmentQuestionGenerateBinding
import com.example.quizzy.models.Questions
import com.example.quizzy.models.Result
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class QuestionGenerateFragment : Fragment() {

    private lateinit var binding : FragmentQuestionGenerateBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionGenerateBinding.inflate(layoutInflater)
        setStatusBarColor()
        setAllTheOptions()

        generateQuestions()
        return binding.root

    }

    private fun generateQuestions() {
        binding.btnGenerateQuestions.setOnClickListener {
            var category = binding.etProductCategory.text.toString()
            val difficulty = binding.etQuestionDifficulty.text.toString()
            val type = binding.etQuestionType.text.toString()
            val numberOfQuestions = binding.etNumberOfQuestions.text.toString()
            var categoryInt = 0
            when(category){
                "Sports" -> categoryInt = 21
                "History" -> categoryInt = 23
                "Politics" -> categoryInt = 24
            }

            var difficultyString = ""
            when(difficulty){
                "Easy" -> difficultyString = "easy"
                "Medium" -> difficultyString = "medium"
                "Hard" -> difficultyString = "hard"
            }

            var typeString = ""
            when(type){
                "Multiple Choice" -> typeString = "multiple"
                "True / False" -> typeString = "multiple"
            }
            val bundle = Bundle()
            bundle.putInt("category" , categoryInt)
            bundle.putString("difficulty" , difficultyString)
            bundle.putString("type" , typeString)
            bundle.putInt("numberOfQ" , numberOfQuestions.toInt())

            findNavController().navigate(R.id.action_questionGenerateFragment_to_quizFragment,bundle)
        }
    }

    private fun setAllTheOptions() {
        val category = ArrayAdapter(requireContext(),R.layout.showing_states_layout,Utils.listOfCategory)
        val difficulty = ArrayAdapter(requireContext(),R.layout.showing_states_layout,Utils.listOfDifficulty)
        val type = ArrayAdapter(requireContext(),R.layout.showing_states_layout,Utils.listOfTypes)
        val numberOfQuestions = ArrayAdapter(requireContext(),R.layout.showing_states_layout,Utils.listOfNumberOfQuestions)

        binding.apply {
            etProductCategory.setAdapter(category)
            etQuestionDifficulty.setAdapter(difficulty)
            etQuestionType.setAdapter(type)
            etNumberOfQuestions.setAdapter(numberOfQuestions)
        }
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