package com.vikk.memorynotes.framework.repository

import com.vikk.memorynotes.framework.database.NoteDAO
import com.vikk.memorynotes.framework.database.NoteEntity
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDAO: NoteDAO): NoteRepositoryInterface {
    override suspend fun addNote(note: NoteEntity) = noteDAO.addNoteEntity(note)

    override suspend fun getNoteById(noteId: Long) = noteDAO.getNoteEntity(noteId)

    override suspend fun getAllNotes() = noteDAO.getAllNoteEntities()

    override suspend fun removeNote(note: NoteEntity) = noteDAO.deleteNoteEntity(note)
}