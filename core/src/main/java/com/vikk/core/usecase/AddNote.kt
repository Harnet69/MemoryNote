package com.vikk.core.usecase

import com.vikk.core.data.Note
import com.vikk.core.repository.NoteRepository

/*
    Used for updating records too, because of Room database
 */
class AddNote(private val noteRepository: NoteRepository) {
    //operator allows to invoke this function as operator on object
    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}