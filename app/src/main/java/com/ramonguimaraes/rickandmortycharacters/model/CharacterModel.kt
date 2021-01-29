package com.ramonguimaraes.rickandmortycharacters.model

data class CharacterModel(

    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
    val episode: ArrayList<String>,
    val location: LocationModel

) {

    val episodeNumber: Int
        get() {
            val splitted = episode[0].split("/")
            return splitted[splitted.size - 1].toInt()
        }


}
