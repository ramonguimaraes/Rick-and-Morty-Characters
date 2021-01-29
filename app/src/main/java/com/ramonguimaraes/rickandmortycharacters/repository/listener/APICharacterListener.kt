package com.ramonguimaraes.rickandmortycharacters.repository.listener

import com.ramonguimaraes.rickandmortycharacters.model.CharacterResponseModel

interface APICharacterListener {

    fun onSuccess(characterResponseModel: CharacterResponseModel)
    fun onFailure(message: String)

}