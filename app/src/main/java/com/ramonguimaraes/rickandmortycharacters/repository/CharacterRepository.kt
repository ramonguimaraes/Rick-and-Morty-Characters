package com.ramonguimaraes.rickandmortycharacters.repository

import android.util.Log
import com.ramonguimaraes.rickandmortycharacters.model.CharacterResponseModel
import com.ramonguimaraes.rickandmortycharacters.repository.listener.APICharacterListener
import com.ramonguimaraes.rickandmortycharacters.repository.service.CharacterService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepository {

    fun getCharacters(page: Int, characterListener: APICharacterListener) {

        val retrofit = RetrofitInstance.getInstance()
        val service = retrofit.create(CharacterService::class.java)
        val call = service.character(page)

        call.enqueue(
            object: Callback<CharacterResponseModel> {
                override fun onResponse(
                    call: Call<CharacterResponseModel>,
                    responseModel: Response<CharacterResponseModel>
                ) {
                    //Log.e("onResponse", responseModel.body()?.results.toString())
                    responseModel.body()?.let { characterListener.onSuccess(it) }
                }

                override fun onFailure(call: Call<CharacterResponseModel>, t: Throwable) {
                   // Log.e("onFailure", t.message.toString())
                    characterListener.onFailure(t.message.toString())
                }

            }

        )

    }

}