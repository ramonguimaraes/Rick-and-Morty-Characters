package com.ramonguimaraes.rickandmortycharacters.view

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ramonguimaraes.rickandmortycharacters.R
import com.ramonguimaraes.rickandmortycharacters.model.CharacterModel
import com.ramonguimaraes.rickandmortycharacters.model.EpisodeModel
import com.ramonguimaraes.rickandmortycharacters.view.adapter.CharactersAdapter
import com.ramonguimaraes.rickandmortycharacters.viewmodel.ApiRepositoryViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var apiViewModel: ApiRepositoryViewModel
    private var list: ArrayList<CharacterModel> = arrayListOf()
    private var episodes: ArrayList<EpisodeModel> = arrayListOf()
    private var layoutManager = LinearLayoutManager(this)

    var loading = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiViewModel = ViewModelProvider(this).get(ApiRepositoryViewModel::class.java)

        apiViewModel.getCharacters()
        getEpisodes()
        observerResponse()

        rv_character_list.adapter = CharactersAdapter(list, episodes)
        rv_character_list.layoutManager = layoutManager

        rv_character_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                val itemCount = layoutManager.itemCount

                if (!loading) {
                    if (lastVisibleItem == itemCount - 1) {
                        getMoreItens()
                        loading = true
                    }
                }
            }
        })
    }

    private fun getEpisodes() {
        apiViewModel.getEpisodes()
        apiViewModel.episodeResponse.observe(
            this,
            {
                apiViewModel.getEpisodes()
                episodes.addAll(it.results)
                rv_character_list.adapter?.notifyDataSetChanged()
            }
        )
    }

    private fun getMoreItens() {
        progress_circular.visibility = View.VISIBLE
        apiViewModel.getCharacters()
        apiViewModel.loading.observe(
            this,
            {
                loading = it
                progress_circular.visibility = View.GONE
            }
        )
    }

    fun observerResponse() {
        apiViewModel.reponse.observe(
            this,
            {
                list.addAll(it.results)
                rv_character_list.adapter?.notifyDataSetChanged()
            }
        )
    }

}