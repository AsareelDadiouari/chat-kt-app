package com.demo.chatktapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.demo.chatktapp.inscriptionFragment.WelcomeFragment
import com.demo.chatktapp.services.FirebaseService
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (FirebaseService.currentUser() == null)
            FirebaseService.authenticate()

        setContentView(R.layout.activity_main)
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        when(isFirstTime()){
            true -> {
                sharedPreferences.getString("deviceID", null) ?: saveId()
                supportFragmentManager.commit {
                    add(R.id.mainActivity, WelcomeFragment())
                    setReorderingAllowed(true)
                }
            }
            false -> {
                toolbarBuild()
            }
        }
    }

    private fun toolbarBuild(){
        toolbar = findViewById(R.id.main_activity_toolbar)
        setSupportActionBar(toolbar)
    }

    private fun saveId(){
        val deviceID = UUID.randomUUID().toString()
        sharedPreferences.edit().putString("deviceID", deviceID).apply()
    }

    private fun isFirstTime(): Boolean {
        return (sharedPreferences.getString("deviceID", null) == null) || (sharedPreferences.getString("username", null) == null)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toobar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                Toast.makeText(applicationContext, "Search icon is clicked", Toast.LENGTH_LONG).show()
            }
            R.id.action_settings -> {
                Toast.makeText(applicationContext, "Parameter icon is clicked", Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}