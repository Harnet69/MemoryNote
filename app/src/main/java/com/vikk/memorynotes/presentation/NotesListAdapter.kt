package com.vikk.memorynotes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchmemorynotes.R
import com.vikk.memorynotes.framework.database.NoteEntity
import java.util.*

class NotesListAdapter(val action: ListAction): RecyclerView.Adapter<NotesListAdapter.NotesListViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<NoteEntity>(){
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerViewDiffer = AsyncListDiffer(this, diffUtil)

    var notes: List<NoteEntity>
        get() = recyclerViewDiffer.currentList
        set(value) = recyclerViewDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NotesListViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesListViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    inner class NotesListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val noteTitle: TextView = itemView.findViewById(R.id.note_item_title)
        private val noteContent: TextView = itemView.findViewById(R.id.note_item_content)
        private val noteDate: TextView = itemView.findViewById(R.id.note_item_date)
        private val noteWordsAmount: TextView = itemView.findViewById(R.id.note_item_words_amount)

        fun bind(note: NoteEntity) {
            itemView.apply {
                noteTitle.text = context.getString(R.string.note_item_title, note.title)
                noteContent.text = context.getString(R.string.note_item_context, note.content)
                noteDate.text = context.getString(R.string.note_item_date, Date(note.updateTime).toString())
//                noteWordsAmount.text = note.wordsCount.toString()
            }

            itemView.setOnClickListener {
                action.onClick(note.id)
            }
        }
    }

    override fun getItemCount(): Int {
      return notes.size
    }
}
