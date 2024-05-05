package com.example.chapter4

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chapter4.db.NoteDatabase
import com.example.chapter4.db.dao.NoteDao
import com.example.chapter4.viewModel.NoteViewModel

class ViewModelFactory private constructor(
    private val noteDao: NoteDao
) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    NoteDatabase.getInstance(context).noteDao
                )
            }
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(NoteViewModel::class.java) -> {
                NoteViewModel(noteDao) as T
            }

            else -> throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}