package com.example.chapter4.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chapter4.model.User

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM user")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    fun login(email: String, password: String): User

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserById(id: Int): User
}