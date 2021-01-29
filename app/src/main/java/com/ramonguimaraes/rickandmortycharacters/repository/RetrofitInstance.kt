package com.ramonguimaraes.rickandmortycharacters.repository

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance private constructor(){

    companion object {

        private lateinit var retrofit: Retrofit
        private val baseUrl = "https://rickandmortyapi.com/api/"

        fun getInstance(): Retrofit {
            if (!::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit

        }

    }

}