package com.vikk.memorynotes.presentation

/*
    Define what item in RecyclerView was clicked
 */
interface ListAction {
    fun onClick(id: Long)
}