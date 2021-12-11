package com.vikk.memorynotes.framework.repository

import com.vikk.memorynotes.framework.database.NoteEntity

interface NoteRepositoryInterface {
    suspend fun addNote(note: NoteEntity): Long?

    suspend fun getNoteById(noteId: Long): NoteEntity?

    suspend fun getAllNotes(): List<NoteEntity>

    suspend fun removeNote(note: NoteEntity)
}