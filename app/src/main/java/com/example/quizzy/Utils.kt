package com.example.quizzy

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.quizzy.databinding.ProgressDialogBinding

object Utils {

    private var dialog : AlertDialog? = null


    val listOfNumberOfQuestions = listOf(
        1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20
    )
    val listOfCategory = listOf(
        "Sports"
        ,"Politics"
        ,"History"
    )

    val listOfDifficulty = listOf(
        "Easy"
        ,"Medium"
        ,"Hard"
    )

    val listOfTypes = listOf(
        "Multiple Choice"
    )

    fun showDialog(context: Context, message: String){
        val progress = ProgressDialogBinding.inflate(LayoutInflater.from(context))
        progress.tvMessage.text = message
        dialog  = AlertDialog.Builder(context).setView(progress.root).setCancelable(false).create()
        dialog!!.show()
    }

    fun hideDialog(){
        dialog?.dismiss()
    }
}