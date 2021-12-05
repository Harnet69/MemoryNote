package com.vikk.core.usecase

import com.vikk.core.data.Note
import com.vikk.core.repository.NoteRepository

class RemoveNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.removeNote(note)
}