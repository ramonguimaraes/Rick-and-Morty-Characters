package com.ramonguimaraes.rickandmortycharacters.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ramonguimaraes.rickandmortycharacters.model.CharacterResponseModel
import com.ramonguimaraes.rickandmortycharacters.model.EpisodeModel
import com.ramonguimaraes.rickandmortycharacters.model.EpisodeResponse
import com.ramonguimaraes.rickandmortycharacters.repository.CharacterRepository
import com.ramonguimaraes.rickandmortycharacters.repository.EpisodeRepository
import com.ramonguimaraes.rickandmortycharacters.repository.listener.APICharacterListener
import com.ramonguimaraes.rickandmortycharacters.repository.listener.APIEpisodeListener

class ApiRepositoryViewModel : ViewModel() {

    private val characterRepository = CharacterRepository()
    private val episodeRepository = EpisodeRepository()

    private var mEpisode = MutableLiveData<List<EpisodeModel>>()
    var episode: LiveData<List<EpisodeModel>> = mEpisode

    private var mResponse = MutableLiveData<CharacterResponseModel>()
    var reponse: LiveData<CharacterResponseModel> = mResponse

    private var mMessage = MutableLiveData<Boolean>()
    var message: LiveData<Boolean> = mMessage

    private var page = 1

    fun getCharacters() {

        characterRepository.getCharacters(page, object : APICharacterListener {
            override fun onSuccess(characterResponseModel: CharacterResponseModel) {
                mResponse.value = characterResponseModel
                page++
            }

            override fun onFailure(message: String) {

            }

        })

    }

    fun getEpisode(page: Int) {

        episodeRepository.getEpisode(page, object : APIEpisodeListener {
            override fun onSuccess(episodeResponse: EpisodeResponse) {
                mEpisode.value = episodeResponse.results
                Log.e("---------------", "$page")



            }

            override fun onFailure(message: String) {
                Log.e("@@@@@@@@@@@@@@@@@@@@@@", "-")

            }
        })


    }

}