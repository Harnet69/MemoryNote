package com.vikk.cleanarchmemorynotes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarchmemorynotes.databinding.FragmentNotesListBinding
import com.vikk.cleanarchmemorynotes.framework.NotesListViewModel

class NotesListFragment : Fragment(), ListAction {

    private val viewModel: NotesListViewModel by viewModels()

    private var notesListAdapter = NotesListAdapter(this)

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
        binding.notesListView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = notesListAdapter
        }

        binding.addNoteFab.setOnClickListener {
            goToNoteDetails()
        }
        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.notesList.observe(this, {notesList ->
            binding.loadingView.visibility = View.GONE
            binding.notesListView.visibility = View.VISIBLE
            notesListAdapter.notes = notesList.sortedByDescending { it.updateTime }
        })
    }

    private fun goToNoteDetails(noteId: Long = 0L) {
        findNavController().navigate(
            NotesListFragmentDirections.actionNotesListFragmentToNoteFragment(
                noteId
            )
        )
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(id: Long) {
        goToNoteDetails(id)
    }
}