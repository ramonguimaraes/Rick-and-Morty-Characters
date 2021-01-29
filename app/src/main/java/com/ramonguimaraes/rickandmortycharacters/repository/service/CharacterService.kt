package com.ramonguimaraes.rickandmortycharacters.repository.service

import com.ramonguimaraes.rickandmortycharacters.model.CharacterResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("character")
    fun character(@Query("page") page: Int): Call<CharacterResponseModel>

}