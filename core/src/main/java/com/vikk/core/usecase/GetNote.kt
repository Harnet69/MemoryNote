package com.vikk.core.usecase

import com.vikk.core.repository.NoteRepository

class GetNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(noteId: Long) = noteRepository.getNoteById(noteId)
}