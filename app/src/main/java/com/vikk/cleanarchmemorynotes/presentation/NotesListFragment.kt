package com.vikk.cleanarchmemorynotes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarchmemorynotes.databinding.FragmentNotesListBinding
import com.vikk.core.data.Note

class NotesListFragment : Fragment() {

    private var notesListAdapter = NotesListAdapter()

    private var _binding: FragmentNotesListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addNoteFab.setOnClickListener {
            goToNoteDetails()
        }

        binding.notesListView.adapter = notesListAdapter
        binding.notesListView.layoutManager = LinearLayoutManager(requireContext())

        //TODO for test purposes only
        notesListAdapter.notes = arrayListOf(
            Note("Title1", "Content1", 123456L, 123457L),
            Note("Title2", "Content2", 123446L, 123447L)
        )
    }

    private fun goToNoteDetails(noteId: Long = 0L) {
        findNavController().navigate(
            NotesListFragmentDirections.actionNotesListFragmentToNoteFragment(
                noteId
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}