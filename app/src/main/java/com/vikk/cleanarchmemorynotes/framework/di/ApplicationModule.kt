package com.vikk.cleanarchmemorynotes.framework.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cleanarchmemorynotes.R
import com.vikk.cleanarchmemorynotes.framework.database.NoteDAO
import com.vikk.cleanarchmemorynotes.framework.database.NotesDatabase
import com.vikk.cleanarchmemorynotes.framework.repository.NoteRepository
import com.vikk.cleanarchmemorynotes.framework.repository.NoteRepositoryInterface
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
    fun injectRepository(noteDAO: NoteDAO) = NoteRepository(noteDAO) as NoteRepositoryInterface

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, NotesDatabase::class.java, "note.db").build()

    @Singleton
    @Provides
    fun injectDao(database: NotesDatabase) = database.noteDAO()


    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
        )
}