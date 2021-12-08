package com.vikk.core.usecase

import com.vikk.core.data.Note

/*
    Counts words in a note include title and content both
 */
class GetWordsCount {
    operator fun invoke(note: Note): Int = getCount(note.title) + getCount(note.content)

    private fun getCount(str: String) = str.split("", "\n")
        .filter {
            it.contains(Regex(".*[a-zA-Z].*"))
        }
        .count()
}