package com.vikk.cleanarchmemorynotes.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vikk.core.data.Note
import com.vikk.core.repository.NoteRepository
import com.vikk.core.usecase.AddNote
import com.vikk.core.usecase.GetAllNotes
import com.vikk.core.usecase.GetNote
import com.vikk.core.usecase.RemoveNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val repository = NoteRepository(RoomNoteDataSource(application))

    //TODO remove it after implementing Dependency Injections
    val useCases = UseCases(
        AddNote(repository),
        GetNote(repository),
        GetAllNotes(repository),
        RemoveNote(repository)
    )

    val isSaved = MutableLiveData<Boolean>()

    val currentNote = MutableLiveData<Note?>()

    fun saveNote(note: Note){
        coroutineScope.launch {
            // isSaved variable is based on returned rowId number
          isSaved.postValue(useCases.addNote(note)!! >= 1L)
        }
    }

    fun getNoteById(id: Long){
        coroutineScope.launch {
            currentNote.postValue(useCases.getNote(id))
        }
    }

    fun deleteNote(note: Note){
        coroutineScope.launch {
            useCases.removeNote(note)
            isSaved.postValue(true)
        }
    }
}