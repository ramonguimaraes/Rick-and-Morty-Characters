package com.ramonguimaraes.rickandmortycharacters.repository.service

import com.ramonguimaraes.rickandmortycharacters.model.EpisodeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodeService {

    @GET("episode")
    fun episodes(@Query("page") page: Int): Call<EpisodeResponse>

}