package com.example.notesapp.model

import androidx.lifecycle.LiveData

class NoteRepository(private val db:AppDatabase) {
    fun upsert(note:Note)=db.noteDao().upsert((note))
    fun delete(note:Note)=db.noteDao().delete(note)
    fun getAllNotes(): LiveData<List<Note>> = db.noteDao().getAll()

}