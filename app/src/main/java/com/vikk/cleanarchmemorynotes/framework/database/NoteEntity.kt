package com.vikk.cleanarchmemorynotes.framework.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vikk.core.data.Note

@Entity(tableName = "note")
data class NoteEntity(
    var title: String,
    var content: String,

    @ColumnInfo(name = "creation_time")
    var creationTime: Long,

    @ColumnInfo(name = "update_time")
    var updateTime: Long,

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
) {
    // create NoteEntity from Note and back
    companion object {
        fun fromNote(note: Note) =
            NoteEntity(note.title, note.content, note.creationTime, note.updateTime, note.id)
    }

    fun toNote() = Note(title, content, creationTime, updateTime, id)
}
