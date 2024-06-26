package com.example.chapter4.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.chapter4.db.dao.NoteDao
import com.example.chapter4.db.dao.UserDao
import com.example.chapter4.model.Notes
import com.example.chapter4.model.User

@Database(entities = [Notes::class, User::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val noteDao: NoteDao

    companion object {
        private const val DATABASE_NAME = "NOTES_DATABASE"

        private var INSTANCES: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase {
            var instance = INSTANCES
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCES = instance
            }
            return instance
        }

    }
}