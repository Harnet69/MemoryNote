package com.vikk.memorynotes.framework.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
)