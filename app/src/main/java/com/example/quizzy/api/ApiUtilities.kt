package com.example.quizzy.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtilities {

    val api : ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl("https://opentdb.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

}