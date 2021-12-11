package com.vikk.memorynotes.framework.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vikk.memorynotes.framework.database.NoteEntity
import com.vikk.memorynotes.framework.repository.NoteRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesListViewModel @Inject constructor(val repository: NoteRepositoryInterface) : ViewModel(){
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val notesList = MutableLiveData<List<NoteEntity>>()

    fun getNotes() {
        coroutineScope.launch {
            //delay for testing purposes
            delay(1000L)
            val notes = repository.getAllNotes()
//            notes.forEach { it.wordsCount = repository.getWordsCount.invoke(it).toLong() }
            notesList.postValue(notes)
        }
    }
}