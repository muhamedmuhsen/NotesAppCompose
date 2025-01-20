package com.example.notesapp.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes")
    fun getAll(): LiveData<List<Note>>

    @Delete
    fun delete(note: Note)

    @Upsert
    fun upsert(note:Note)
}