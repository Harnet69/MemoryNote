package com.vikk.cleanarchmemorynotes.framework

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vikk.core.data.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(var useCases: UseCases): ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

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