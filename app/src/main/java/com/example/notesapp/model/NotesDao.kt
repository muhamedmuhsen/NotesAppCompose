package com.example.notesapp.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes")
    fun getAll(): LiveData<List<Note>>

    @Delete
    fun delete(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(note:Note)

    @Query("SELECT * FROM notes WHERE id = :id LIMIT 1")
    fun getNoteById(id: Int): LiveData<Note?>
}