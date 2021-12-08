package com.vikk.cleanarchmemorynotes.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vikk.cleanarchmemorynotes.framework.di.ApplicationModule
import com.vikk.cleanarchmemorynotes.framework.di.DaggerViewModelComponent
import com.vikk.core.data.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel(application: Application): AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    //Dependency Injections approach
    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(getApplication()))
            .build()
            .inject(this)
    }

    //    //instantiation approach
//    private val repository = NoteRepository(RoomNoteDataSource(application))

//    val useCases = UseCases(
//        AddNote(repository),
//        GetNote(repository),
//        GetAllNotes(repository),
//        RemoveNote(repository)
//    )

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