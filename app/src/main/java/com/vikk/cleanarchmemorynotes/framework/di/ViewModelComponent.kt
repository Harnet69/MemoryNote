package com.vikk.cleanarchmemorynotes.framework.di

import com.vikk.cleanarchmemorynotes.framework.NoteViewModel
import com.vikk.cleanarchmemorynotes.framework.NotesListViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {
    // argument is where do you inject
    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: NotesListViewModel)

}