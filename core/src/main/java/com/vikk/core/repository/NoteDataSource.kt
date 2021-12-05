package com.vikk.core.repository

import com.vikk.core.data.Note

/*
    Universal functionality for all components
 */
interface NoteDataSource {

    suspend fun add(note: Note)

    suspend fun getById(id: Long): Note?

    suspend fun getAll(): List<Note>

    suspend fun remove(note: Note){

    }
}