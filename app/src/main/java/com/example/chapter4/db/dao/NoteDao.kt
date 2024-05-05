package com.example.chapter4.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.chapter4.model.Notes

@Dao
interface NoteDao {
    @Insert
    fun insert(notes: Notes)

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Notes>>

    @Query("UPDATE notes SET note_title = :title, content = :content WHERE id = :id")
    fun update(title: String, content: String, id: Int)

    @Delete
    fun delete(notes: Notes)
}