package com.example.chapter4


import android.app.Application
import android.content.Context
import android.util.Log
import com.example.chapter4.db.NoteDatabase


class App : Application() {
    val dataBase by lazy { NoteDatabase.getInstance(this) }

    override fun onCreate() {
        super.onCreate()
        Log.d("SIMPLE_TAG", "onCreate: ")
        SharedPreference.init(this)
    }
}
