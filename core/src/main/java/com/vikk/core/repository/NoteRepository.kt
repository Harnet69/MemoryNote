package com.vikk.core.repository

import com.vikk.core.data.Note

/*
    Whoever implements NoteDataSource interface can have an access for this CRUD functionality
 */
class NoteRepository(
    private val noteDataSource: NoteDataSource
) {
    suspend fun addNote(note: Note) = noteDataSource.add(note)

    suspend fun getNoteById(noteId: Long) = noteDataSource.getById(noteId)

    suspend fun getAllNotes() = noteDataSource.getAll()

    suspend fun removeNote(note: Note) = noteDataSource.remove(note)
}