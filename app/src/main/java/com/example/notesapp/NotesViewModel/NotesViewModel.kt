package com.example.notesapp.NotesViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.notesapp.model.AppDatabase
import com.example.notesapp.model.Note

class  NoteViewModel(app: Application) : AndroidViewModel(app) {
    private val context = getApplication<Application>().applicationContext

    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    ).build()
    val allNotes = db.noteDao().getAll()
    fun upsert(note: Note) = db.noteDao().upsert(note)
    fun delete(note: Note) = db.noteDao().delete(note)
    fun getAllNotes() = db.noteDao().getAll()
}