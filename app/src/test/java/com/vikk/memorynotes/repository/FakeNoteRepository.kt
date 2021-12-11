package com.vikk.memorynotes.repository

import androidx.lifecycle.MutableLiveData
import com.vikk.cleanarchmemorynotes.framework.database.NoteEntity
import com.vikk.cleanarchmemorynotes.framework.repository.NoteRepositoryInterface

class FakeNoteRepository: NoteRepositoryInterface {
    private val notes = arrayListOf<NoteEntity>()

    private val artsLiveData = MutableLiveData<List<NoteEntity>>(notes)

    override suspend fun addNote(note: NoteEntity): Long {
        notes.add(note)
        refreshData()
        return 0L
    }

    override suspend fun getNoteById(noteId: Long): NoteEntity = notes.first { it.id == noteId }

    override suspend fun getAllNotes(): List<NoteEntity> = notes

    override suspend fun removeNote(note: NoteEntity) {
        notes.remove(note)
        refreshData()
    }

    private fun refreshData(){
        artsLiveData.postValue(notes)
    }
}