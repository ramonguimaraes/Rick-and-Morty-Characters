package com.ramonguimaraes.rickandmortycharacters.repository.listener

import com.ramonguimaraes.rickandmortycharacters.model.EpisodeResponse

interface APIEpisodeListener {
    fun onSuccess(episodeResponse: EpisodeResponse)
    fun onFailure(message: String)
}