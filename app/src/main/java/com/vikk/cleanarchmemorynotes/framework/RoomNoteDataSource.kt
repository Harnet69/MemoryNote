package com.vikk.cleanarchmemorynotes.framework

import android.content.Context
import com.vikk.cleanarchmemorynotes.framework.database.DatabaseService
import com.vikk.cleanarchmemorynotes.framework.database.NoteEntity
import com.vikk.core.data.Note
import com.vikk.core.repository.NoteDataSource

/*
    Implement all methods from NoteDataSource interface by noteDao functions
 */
class RoomNoteDataSource(context: Context): NoteDataSource {
    private val noteDao = DatabaseService.getInstance(context).noteDAO()

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun getById(id: Long) = noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll() = noteDao.getAllNoteEntities().map { it.toNote() }

    override suspend fun remove(note: Note) = noteDao.deleteNoteEntity(NoteEntity.fromNote(note))

}