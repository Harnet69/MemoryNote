package com.vikk.cleanarchmemorynotes.framework.di

import android.app.Application
import com.vikk.cleanarchmemorynotes.framework.RoomNoteDataSource
import com.vikk.core.repository.NoteRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(app: Application) = NoteRepository(RoomNoteDataSource(app))
}