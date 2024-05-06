package com.example.chapter4.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.chapter4.db.dao.UserDao
import com.example.chapter4.model.User

class AuthViewModel(private val userDao: UserDao) : ViewModel() {
    fun login(email: String, password: String): User {
        val user = userDao.login(email, password)
        Log.d("AuthViewModel", "login: $user")
        return user
    }

    fun register(user: User) {
        return userDao.insert(user)
    }

    fun getAllUsers() = userDao.getAllUsers()

    fun getUserById(id: Int) = userDao.getUserById(id)
}