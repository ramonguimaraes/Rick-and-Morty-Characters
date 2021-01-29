package com.ramonguimaraes.rickandmortycharacters.repository.data

import android.util.Log
import androidx.paging.PagingSource
import com.ramonguimaraes.rickandmortycharacters.model.CharacterResponseModel
import com.ramonguimaraes.rickandmortycharacters.repository.RetrofitInstance
import com.ramonguimaraes.rickandmortycharacters.repository.listener.APICharacterListener
import com.ramonguimaraes.rickandmortycharacters.repository.service.CharacterService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharactersPagingSource(private val characterListener: APICharacterListener): PagingSource<Int, CharacterResponseModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResponseModel> {

        val retrofit = RetrofitInstance.getInstance()
        val service = retrofit.create(CharacterService::class.java)
        val call = service.character(params.key ?: 1)

        call.enqueue(
            object: Callback<CharacterResponseModel> {
                override fun onResponse(
                    call: Call<CharacterResponseModel>,
                    responseModel: Response<CharacterResponseModel>
                ) {
                    Log.e("onResponse", responseModel.body()?.results.toString())
                    responseModel.body()?.let { characterListener.onSuccess(it) }
                }

                override fun onFailure(call: Call<CharacterResponseModel>, t: Throwable) {
                    Log.e("onFailure", t.message.toString())
                    characterListener.onFailure(t.message.toString())
                }

            }

        )


    }
}