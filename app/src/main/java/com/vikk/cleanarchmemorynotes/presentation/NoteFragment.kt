package com.vikk.cleanarchmemorynotes.presentation

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cleanarchmemorynotes.databinding.FragmentNoteBinding
import com.vikk.cleanarchmemorynotes.framework.NoteViewModel
import com.vikk.core.data.Note

class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NoteViewModel by viewModels()

    private val currentNote = Note("", "", 0L, 0L)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDoneKey()

        binding.saveNoteFab.setOnClickListener {
            saveNote()
        }
        observeViewModel()
    }

    private fun saveNote(){
        if (binding.noteTitle.text.isNotBlank() && binding.noteContent.text.isNotBlank()) {
            val time = System.currentTimeMillis()
            currentNote.title = binding.noteTitle.text.toString()
            currentNote.content = binding.noteContent.text.toString()
            currentNote.updateTime = time
            // if it's a newly created note
            if (currentNote.id == 0L) {
                currentNote.creationTime = time
            }

            viewModel.saveNote(currentNote)
        } else {
            Toast.makeText(context, "Field can't be empty", Toast.LENGTH_LONG).show()
        }
    }

    private fun observeViewModel() {
        viewModel.isSaved.observe(this, {isSaved ->
            if (isSaved) {
                Toast.makeText(context, "Note was added", Toast.LENGTH_LONG).show()
                hideKeyboard()
                findNavController().popBackStack()
            } else {
                Toast.makeText(context, "Smth went wrong, try again later", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    private fun hideKeyboard(){
        val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.noteTitle.windowToken, 0)
    }

    // if user press on DONE
    private fun setDoneKey(){
        binding.noteContent.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                saveNote()
                return@setOnEditorActionListener true
            }
            false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}