package com.demo.chatktapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Determine if the app was opened for the first time
         */
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        sharedEditor = sharedPreferences.edit()

        when(isFirstTime()){
            true -> {
                setContentView(R.layout.fragment_welcome)
            }
            false -> {
                setContentView(R.layout.activity_main)
            }
        }
    }

    private fun isFirstTime(): Boolean {
        if (sharedPreferences.getBoolean("firstTime", true)){
            sharedEditor.putBoolean("firstTime", false)
            sharedEditor.commit()
            sharedEditor.apply()
            return true
        }
        return false
    }
}