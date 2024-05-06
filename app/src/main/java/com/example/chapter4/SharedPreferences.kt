package com.example.simpleviewmodelapp

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object SharedPreference {
    private lateinit var sharedPreferences: SharedPreferences
    private const val PREFS_NAME = "PREFS_NAME"

    const val ISLOGIN = "IS_LOGIN"
    fun init(context: Context){
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    var isLogin: Boolean
        set(value) {
            sharedPreferences.edit {
                putBoolean(ISLOGIN, value)
            }
        }
        get() = sharedPreferences.getBoolean(ISLOGIN, false)


}