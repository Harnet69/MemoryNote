package com.vikk.cleanarchmemorynotes.framework.di

import com.vikk.cleanarchmemorynotes.framework.UseCases
import com.vikk.core.repository.NoteRepository
import com.vikk.core.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun getUseCases(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetNote(repository),
        GetAllNotes(repository),
        RemoveNote(repository),
        GetWordsCount()
    )
}