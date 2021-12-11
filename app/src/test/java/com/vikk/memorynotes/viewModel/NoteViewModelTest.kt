package com.vikk.memorynotes.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.vikk.cleanarchmemorynotes.framework.NoteViewModel
import com.vikk.cleanarchmemorynotes.framework.database.NoteEntity
import com.vikk.memorynotes.MainCoroutineRule
import com.vikk.memorynotes.getOrAwaitValueTest
import com.vikk.memorynotes.repository.FakeNoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NoteViewModelTest {

    private val exampleNote = NoteEntity("Test title", "Test content", 0L, 0L, 1L)

    // Set only one, main thread
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Set only one, main thread
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: NoteViewModel

    @Before
    fun setup(){
        //Test Doubles - FakeNoteRepository
        viewModel = NoteViewModel(FakeNoteRepository())
    }

    @Test
    fun `add note`(){
        viewModel.addNote(exampleNote)
        val isSaved = viewModel.isSaved.getOrAwaitValueTest()
        println(isSaved)
        assertThat(isSaved).isTrue()
    }

    @Test
    fun `get note by id receive note`(){
        viewModel.addNote(exampleNote)
        viewModel.getNoteById(1L)
        val currentNote = viewModel.currentNote.getOrAwaitValueTest()
        assertThat(currentNote).isEqualTo(exampleNote)

    }

//    @Test
//    fun `insert art without year returns error`(){
//        viewModel.addArt("Mona Lisa", "Da Vinci", "")
//        // convert LiveData in regular data
//        val isAdded = viewModel.mInsertArtMsg.getOrAwaitValueTest()
//        assertThat(isAdded.status).isEqualTo(Status.ERROR)
//    }
//
//    @Test
//    fun `insert art without name returns error`(){
//        viewModel.addArt("", "Da Vinci", "1503")
//        val isAdded = viewModel.mInsertArtMsg.getOrAwaitValueTest()
//        assertThat(isAdded.status).isEqualTo(Status.ERROR)
//
//    }
//
//    @Test
//    fun `insert art without artistsName returns error`(){
//        viewModel.addArt("", "Da Vinci", "1503")
//        val isAdded = viewModel.mInsertArtMsg.getOrAwaitValueTest()
//        assertThat(isAdded.status).isEqualTo(Status.ERROR)
//    }
}