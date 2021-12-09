package com.vikk.cleanarchmemorynotes.framework

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vikk.core.data.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesListViewModel @Inject constructor(var useCases: UseCases) : ViewModel(){
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

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