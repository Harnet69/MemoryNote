package com.vikk.cleanarchmemorynotes.framework.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface NoteDAO {
    @Insert(onConflict = REPLACE)
    fun addNoteEntity(noteEntity: NoteEntity): Long?

    @Query("SELECT * FROM note WHERE id = :id")
    fun getNoteEntity(id: Long): NoteEntity?

    @Query("SELECT * FROM note")
    fun getAllNoteEntities(): List<NoteEntity>

    @Delete
    fun deleteNoteEntity(noteEntity: NoteEntity)
}