package com.vikk.memorynotes.framework.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vikk.memorynotes.framework.database.NoteEntity
import com.vikk.memorynotes.framework.repository.NoteRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(var repository: NoteRepositoryInterface) : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val isSaved = MutableLiveData<Boolean>()

    val currentNote = MutableLiveData<NoteEntity?>()

    fun addNote(note: NoteEntity) {
        coroutineScope.launch {
            repository.addNote(note)
            isSaved.postValue(true)
        }
    }

    fun getNoteById(id: Long) {
        coroutineScope.launch {
            currentNote.postValue(repository.getNoteById(id))
        }
    }

    fun deleteNote(note: NoteEntity) {
        coroutineScope.launch {
            repository.removeNote(note)
            isSaved.postValue(true)
        }
    }
}