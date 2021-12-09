package com.vikk.cleanarchmemorynotes.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vikk.cleanarchmemorynotes.framework.di.ApplicationModule
import com.vikk.cleanarchmemorynotes.framework.di.DaggerViewModelComponent
import com.vikk.core.data.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesListViewModel(application: Application) : AndroidViewModel(application) {
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
//
//    val useCases = UseCases(
//        AddNote(repository),
//        GetNote(repository),
//        GetAllNotes(repository),
//        RemoveNote(repository)
//    )

    val notesList = MutableLiveData<List<Note>>()

    fun getNotes() {
        coroutineScope.launch {
            //delay for testing purposes
            delay(1000L)
            val notes = useCases.getAllNotes()
            notes.forEach { it.wordsCount = useCases.getWordsCount.invoke(it).toLong() }
            notesList.postValue(notes)
        }
    }
}