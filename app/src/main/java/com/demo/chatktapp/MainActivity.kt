package com.demo.chatktapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.demo.chatktapp.adapters.ViewPagerAdapter
import com.demo.chatktapp.inscriptionFragment.WelcomeFragment
import com.demo.chatktapp.services.FirebaseService
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var tableLayout: TabLayout
    private lateinit var tabViewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (FirebaseService.currentUser() == null)
            FirebaseService.authenticate()

        setContentView(R.layout.activity_main)
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        when (isFirstTime()) {
            true -> {
                sharedPreferences.getString("deviceID", null) ?: saveId()
                supportFragmentManager.commit {
                    add(R.id.mainActivity, WelcomeFragment())
                    setReorderingAllowed(true)
                }
            }
            false -> {
                tableLayout = findViewById(R.id.main_activity_tab_layout)
                tabViewPager = findViewById(R.id.tab_viewPager)

                tabViewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
                tabViewPager.offscreenPageLimit = 2; //Put tabs in memory !important for smoothness

                TabLayoutMediator(tableLayout, tabViewPager) { tab, position ->
                    when (position) {
                        0 -> {
                            tab.text = getString(R.string.tab_item_messages)
                        }
                        1 -> {
                            tab.text = getString(R.string.tab_item_contacts)
                        }
                    }
                }.attach()

                toolbarBuild()
                onClickTabs()
            }
        }
    }

    private fun toolbarBuild() {
        toolbar = findViewById(R.id.main_activity_toolbar)
        setSupportActionBar(toolbar)
    }

    private fun saveId() {
        val deviceID = UUID.randomUUID().toString()
        sharedPreferences.edit().putString("deviceID", deviceID).apply()
    }

    private fun isFirstTime(): Boolean {
        return (sharedPreferences.getString(
            "deviceID",
            null
        ) == null) || (sharedPreferences.getString("username", null) == null)
    }

    private fun onClickTabs() {
        tableLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        Log.d("TABS", "Messages Item")
                    }
                    1 -> {
                        Log.d("TABS", "Contact Item")
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toobar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                Toast.makeText(applicationContext, "Search icon is clicked", Toast.LENGTH_LONG)
                    .show()
            }
            R.id.action_settings -> {
                Toast.makeText(applicationContext, "Parameter icon is clicked", Toast.LENGTH_LONG)
                    .show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}