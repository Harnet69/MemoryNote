package com.vikk.cleanarchmemorynotes.framework

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vikk.cleanarchmemorynotes.framework.database.NoteEntity
import com.vikk.cleanarchmemorynotes.framework.repository.NoteRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(var repository: NoteRepositoryInterface): ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val isSaved = MutableLiveData<Boolean>()

    val currentNote = MutableLiveData<NoteEntity?>()

    fun saveNote(note: NoteEntity){
        coroutineScope.launch {
            // isSaved variable is based on returned rowId number
          isSaved.postValue(repository.addNote(note)!! >= 1L)
        }
    }

    fun getNoteById(id: Long){
        coroutineScope.launch {
            currentNote.postValue(repository.getNoteById(id))
        }
    }

    fun deleteNote(note: NoteEntity){
        coroutineScope.launch {
            repository.removeNote(note)
            isSaved.postValue(true)
        }
    }
}