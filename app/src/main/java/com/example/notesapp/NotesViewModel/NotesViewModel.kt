package com.example.notesapp.NotesViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapp.model.AppDatabase
import com.example.notesapp.model.Note
import com.example.notesapp.model.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class NoteViewModel(app: Application) : AndroidViewModel(app) {
    private val repository: NoteRepository

    init {
        val db = AppDatabase.getDatabase(app.applicationContext)
        repository = NoteRepository(db)
    }


    fun upsert(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.upsert(note)
        }
    }

    fun delete(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(note)
        }
    }

    open fun getAllNotes(): LiveData<List<Note>> = repository.getAllNotes()

}