package com.example.chapter4

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chapter4.db.NoteDatabase
import com.example.chapter4.db.dao.NoteDao
import com.example.chapter4.db.dao.UserDao
import com.example.chapter4.viewModel.AuthViewModel
import com.example.chapter4.viewModel.NoteViewModel

class ViewModelFactory private constructor(
    private val noteDao: NoteDao,
    private val userDao: UserDao
) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    NoteDatabase.getInstance(context).noteDao,
                    NoteDatabase.getInstance(context).userDao
                )
            }
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(NoteViewModel::class.java) -> {
                NoteViewModel(noteDao) as T
            }
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> {
                AuthViewModel(userDao) as T
            }
            else -> throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}