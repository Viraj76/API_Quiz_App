package com.example.quizzy.api

import com.example.quizzy.models.Questions
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {


    @GET("/api.php?amount=10")
     fun getAllQuestions(
        @Query("amount") amount : Int,
        @Query("category") category : Int,
        @Query("difficulty") difficulty : String,
        @Query("type") type : String,
    ):Call<Questions>
}