package com.demo.chatktapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.fragment.NavHostFragment
import com.demo.chatktapp.inscriptionFragment.UsernameFragment
import com.demo.chatktapp.inscriptionFragment.WelcomeFragment

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * Determine if the app was opened for the first time
         */
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        sharedEditor = sharedPreferences.edit()

        setContentView(R.layout.activity_main)
        supportFragmentManager.commit {
            add(R.id.mainActivity, WelcomeFragment())
            setReorderingAllowed(true)
        }
    }

    override fun onStart() {
        super.onStart()
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

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.button_next -> {
                Toast.makeText(this, "Click on button next", Toast.LENGTH_LONG).show()
            }
        }
    }
}