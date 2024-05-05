package com.example.chapter4.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chapter4.db.dao.NoteDao
import com.example.chapter4.model.Notes

class NoteViewModel(val noteDao: NoteDao) : ViewModel() {
    private val _notes: MutableLiveData<List<Notes>> = MutableLiveData()
    val notes get() = _notes

    fun addNote(notes: Notes) {
        noteDao.insert(notes)
    }

    fun getAllNotes() = noteDao.getAllNotes()

    fun update(title: String, content: String, id: Int) {
        noteDao.update(title, content, id)
    }

    fun delete(notes: Notes) {
        noteDao.delete(notes)
    }

    fun insert(notes: Notes) {
        noteDao.insert(notes)
    }
}