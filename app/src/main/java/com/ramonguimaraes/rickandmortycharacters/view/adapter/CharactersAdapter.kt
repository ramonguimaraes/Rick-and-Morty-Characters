package com.ramonguimaraes.rickandmortycharacters.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ramonguimaraes.rickandmortycharacters.R
import com.ramonguimaraes.rickandmortycharacters.model.CharacterModel
import com.ramonguimaraes.rickandmortycharacters.model.EpisodeModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_list_item.view.*

class CharactersAdapter(
    private val list: ArrayList<CharacterModel>,
    val episodes: ArrayList<EpisodeModel>
) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    class CharacterViewHolder(
        view: View,
        val context: Context,
        val episodes: ArrayList<EpisodeModel>
    ) : RecyclerView.ViewHolder(view) {

        fun bindData(character: CharacterModel) {

            itemView.character_name.text = character.name

            val episode: String? = episodes.find { ep ->
                ep.id == character.episodeNumber
            }?.name

            itemView.character_first_see.text = if (episode.isNullOrEmpty()) "Episode" else episode

            itemView.character_location.text = character.location.name
            itemView.character_status_text.text = character.status
            itemView.character_specie.text = character.species

            Picasso.get().load(character.image).into(itemView.character_image)

            itemView.character_status_icon.setColorFilter(

                context.getColor(
                    if (character.status == "Alive") R.color.statusAlive
                    else if (character.status == "Dead") R.color.statusDead
                    else R.color.statusUnknow
                )

            )

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.character_list_item, parent, false)
        return CharacterViewHolder(view, parent.context, episodes)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}