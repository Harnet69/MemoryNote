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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NotesListViewModel(application: Application) : AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val repository = NoteRepository(RoomNoteDataSource(application))

    //remove it after implementing Dependency Injections
    val useCases = UseCases(
        AddNote(repository),
        GetNote(repository),
        GetAllNotes(repository),
        RemoveNote(repository)
    )

    val notesList = MutableLiveData<List<Note>>()

    fun getNotes() {
        coroutineScope.launch {
            //delay for testing purposes
            delay(1000L)
            notesList.postValue(useCases.getAllNotes())
        }
    }
}