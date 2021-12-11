package com.vikk.cleanarchmemorynotes.presentation

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cleanarchmemorynotes.R
import com.example.cleanarchmemorynotes.databinding.FragmentNoteBinding
import com.vikk.cleanarchmemorynotes.framework.NoteViewModel
import com.vikk.cleanarchmemorynotes.framework.database.NoteEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NoteViewModel by viewModels()

    private var currentNote = NoteEntity("", "", 0L, 0L)

    // for editing purposes
    private var noteId: Long = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //option menu
        setHasOptionsMenu(true)

        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDoneKey()

        // retrieve a note for edit if user come from notes list
        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
            if (noteId != 0L) viewModel.getNoteById(noteId)
        }

        binding.saveNoteFab.setOnClickListener {
            saveNote()
        }
        observeViewModel()
    }

    private fun saveNote() {
        if (binding.noteTitle.text.isNotBlank() && binding.noteContent.text.isNotBlank()) {
            val time = System.currentTimeMillis()
            currentNote.title = binding.noteTitle.text.toString()
            currentNote.content = binding.noteContent.text.toString()
            currentNote.updateTime = time
            // if it's a newly created note
            if (currentNote.id == 0L) {
                currentNote.creationTime = time
            }

            viewModel.addNote(currentNote)
        } else {
            Toast.makeText(context, "Field can't be empty", Toast.LENGTH_LONG).show()
        }
    }

    private fun observeViewModel() {
        viewModel.isSaved.observe(this, { isSaved ->
            if (isSaved) {
                Toast.makeText(context, "Done", Toast.LENGTH_LONG).show()
                hideKeyboard()
                findNavController().popBackStack()
            } else {
                Toast.makeText(context, "Smth went wrong, try again later", Toast.LENGTH_LONG)
                    .show()
            }
        })

        viewModel.currentNote.observe(this, { note ->
            note?.let {
                currentNote = it
                binding.noteTitle.setText(it.title, TextView.BufferType.EDITABLE)
                binding.noteContent.setText(it.content, TextView.BufferType.EDITABLE)
            }
        })
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.noteTitle.windowToken, 0)
    }

    // if user press on DONE
    private fun setDoneKey() {
        binding.noteContent.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                saveNote()
                return@setOnEditorActionListener true
            }
            false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteNote -> {
                // dialog box
                if (context != null && noteId != 0L) {
                    //if we already have a note !!! import Androidx.appcompat.app
                    AlertDialog.Builder(context!!)
                        .setTitle("Delete note")
                        .setMessage("Do you really want to delete the note?")
                        .setPositiveButton("Yes") { _, _ -> viewModel.deleteNote(currentNote) }
                        .setNegativeButton("No") { _, _ -> }
                        .create()
                        .show()
                }
            }
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}