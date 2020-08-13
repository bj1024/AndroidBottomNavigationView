package com.testdomain.androidbottomnavigationviewexample

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        Log.d(TAG,"OnNavigationItemSelected ${item.itemId}")
        when(item.itemId){

            R.id.navigation_dashboard -> {

            }
            R.id.navigation_home -> {
                Log.d(TAG,"OnNavigationItemSelected navigation_home")
            }
            R.id.navigation_myitem ->{
                Log.d(TAG,"OnNavigationItemSelected navigation_myitem")
            }
            R.id.navigation_notifications ->{
                Log.d(TAG,"OnNavigationItemSelected navigation_dashboard")
            }

        }
        true


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)

//        setupWithNavController とsetOnNavigationItemSelectedListenerは同時に利用できない。
//        navController に addOnDestinationChangedListenerを入れる必要がある。
//        navView.setupWithNavController(navController)


    }
}