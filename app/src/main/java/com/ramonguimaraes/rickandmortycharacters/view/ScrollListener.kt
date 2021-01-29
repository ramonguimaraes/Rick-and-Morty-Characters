package com.ramonguimaraes.rickandmortycharacters.view

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class ScrollListener(val layoutManager: RecyclerView.LayoutManager): RecyclerView.OnScrollListener() {

    val isDone: Boolean = false
    val isLoading: Boolean = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

    }
}