package com.ramonguimaraes.rickandmortycharacters.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ramonguimaraes.rickandmortycharacters.model.CharacterResponseModel
import com.ramonguimaraes.rickandmortycharacters.model.EpisodeResponse
import com.ramonguimaraes.rickandmortycharacters.repository.CharacterRepository
import com.ramonguimaraes.rickandmortycharacters.repository.EpisodeRepository
import com.ramonguimaraes.rickandmortycharacters.repository.listener.APICharacterListener
import com.ramonguimaraes.rickandmortycharacters.repository.listener.APIEpisodeListener

class ApiRepositoryViewModel : ViewModel() {

    private val characterRepository = CharacterRepository()
    private val episodeRepository = EpisodeRepository()

    private var mResponse = MutableLiveData<CharacterResponseModel>()
    var reponse: LiveData<CharacterResponseModel> = mResponse

    private var mEpisodeResponse = MutableLiveData<EpisodeResponse>()
    var episodeResponse: LiveData<EpisodeResponse> = mEpisodeResponse

    private var mLoading = MutableLiveData<Boolean>()
    var loading: LiveData<Boolean> = mLoading

    private var page = 1
    private var epPage = 1

    fun getCharacters() {

        characterRepository.getCharacters(page, object : APICharacterListener {
            override fun onSuccess(characterResponseModel: CharacterResponseModel) {
                mResponse.value = characterResponseModel
                page++
                Log.e("Page", "$page")
                mLoading.value = false
            }

            override fun onFailure(message: String) {

            }

        })
    }

    fun getEpisodes() {
        episodeRepository.getEpisode(epPage, object : APIEpisodeListener {
            override fun onSuccess(episodeResponse: EpisodeResponse) {
                mEpisodeResponse.value = episodeResponse
                epPage++
            }

            override fun onFailure(message: String) {

            }
        })
    }


}