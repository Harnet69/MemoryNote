package com.vikk.cleanarchmemorynotes.framework.repository

import com.vikk.cleanarchmemorynotes.framework.database.NoteEntity

interface NoteRepositoryInterface {
    suspend fun addNote(note: NoteEntity): Long?

    suspend fun getNoteById(noteId: Long): NoteEntity?

    suspend fun getAllNotes(): List<NoteEntity>

    suspend fun removeNote(note: NoteEntity)
}