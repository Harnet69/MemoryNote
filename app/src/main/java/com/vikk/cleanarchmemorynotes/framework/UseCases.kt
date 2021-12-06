package com.vikk.cleanarchmemorynotes.framework

import com.vikk.core.usecase.AddNote
import com.vikk.core.usecase.GetAllNotes
import com.vikk.core.usecase.GetNote
import com.vikk.core.usecase.RemoveNote

/*
    for Dependency injection purposes
    contains all use cases classes from core module for simplicity
    allows to instantiate use cases and use them in ViewModel
 */
data class UseCases(
    val addNote: AddNote,
    val getNote: GetNote,
    val getAllNotes: GetAllNotes,
    val removeNote: RemoveNote
)
