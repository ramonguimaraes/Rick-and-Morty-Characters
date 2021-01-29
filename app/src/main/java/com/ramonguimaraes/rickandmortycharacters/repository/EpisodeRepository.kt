package com.ramonguimaraes.rickandmortycharacters.repository

import android.util.Log
import com.ramonguimaraes.rickandmortycharacters.model.EpisodeResponse
import com.ramonguimaraes.rickandmortycharacters.repository.listener.APIEpisodeListener
import com.ramonguimaraes.rickandmortycharacters.repository.service.EpisodeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpisodeRepository {

    fun getEpisode(page: Int, episodeListener: APIEpisodeListener) {

        val retrofit = RetrofitInstance.getInstance()
        val service = retrofit.create(EpisodeService::class.java)
        val call = service.episodes(page)

        call.enqueue(
            object : Callback<EpisodeResponse> {
                override fun onResponse(
                    call: Call<EpisodeResponse>,
                    response: Response<EpisodeResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { episodeListener.onSuccess(it) }
                        Log.e("Episodio", response.body().toString())
                    } else {
                        episodeListener.onFailure("Erro na requisicao")
                    }
                }

                override fun onFailure(call: Call<EpisodeResponse>, t: Throwable) {
                    episodeListener.onFailure(t.message.toString())
                }

            }
        )

    }


}