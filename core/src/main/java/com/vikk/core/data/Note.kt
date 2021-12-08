package com.vikk.core.data

/*
    Model - simple POJO object
 */
data class Note(
    var title: String,
    var content: String,
    var creationTime: Long,
    var updateTime: Long,
    val id: Long = 0,
    var wordsCount: Long = 0L
)