package com.example.myapplication.data

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KinopoiskApi {
    private const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/"

    val retrofitService: KinopoiskApiService by lazy {
        retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()
            .create(KinopoiskApiService::class.java)
    }
}