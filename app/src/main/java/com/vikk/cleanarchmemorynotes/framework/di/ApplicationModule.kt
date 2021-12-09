package com.vikk.cleanarchmemorynotes.framework.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cleanarchmemorynotes.R
import com.vikk.cleanarchmemorynotes.framework.RoomNoteDataSource
import com.vikk.cleanarchmemorynotes.framework.UseCases
import com.vikk.cleanarchmemorynotes.framework.database.DatabaseService
import com.vikk.core.repository.NoteRepository
import com.vikk.core.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule{

    @Singleton
    @Provides
    fun provideRepository(app: Application) = NoteRepository(RoomNoteDataSource(app))

    @Singleton
    @Provides
    fun provideUseCases(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetNote(repository),
        GetAllNotes(repository),
        RemoveNote(repository),
        GetWordsCount()
    )

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DatabaseService::class.java, "note.db").build()




    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
        )
}