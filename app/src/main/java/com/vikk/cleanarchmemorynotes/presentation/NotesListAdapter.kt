package com.vikk.cleanarchmemorynotes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchmemorynotes.R
import com.vikk.core.data.Note
import java.util.*

class NotesListAdapter: RecyclerView.Adapter<NotesListAdapter.NotesListViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerViewDiffer = AsyncListDiffer(this, diffUtil)

    var notes: List<Note>
        get() = recyclerViewDiffer.currentList
        set(value) = recyclerViewDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NotesListViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesListViewHolder, position: Int) {
        val noteTitle = holder.itemView.findViewById<TextView>(R.id.note_item_title)
        val noteContent = holder.itemView.findViewById<TextView>(R.id.note_item_content)
        val noteDate = holder.itemView.findViewById<TextView>(R.id.note_item_date)

        val note = notes[position]

        holder.itemView.apply {
            noteTitle.text = context.getString(R.string.note_item_title, note.title)
            noteContent.text = context.getString(R.string.note_item_context, note.content)
            noteDate.text = context.getString(R.string.note_item_date, Date(note.updateTime).toString())
        }
    }

    class NotesListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int {
      return notes.size
    }
}
