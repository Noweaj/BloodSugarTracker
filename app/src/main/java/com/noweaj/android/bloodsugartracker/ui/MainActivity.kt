package com.noweaj.android.bloodsugartracker.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.noweaj.android.bloodsugartracker.R
import java.util.*

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_main_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(navController.graph)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.fragment_main_chart,
            R.id.fragment_main_event,
            R.id.fragment_main_settings
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.d(TAG, "onBackPressed")
    }

//    private lateinit var mainHandler: Handler
//    private val runner = object: Runnable{
//        override fun run() {
//            Log.d(TAG, "${Calendar.getInstance().timeInMillis}")
//            mainHandler.postDelayed(this, 1000)
//        }
//    }
    
    override fun onResume() {
        super.onResume()
        
//        mainHandler = Handler(Looper.getMainLooper())
//        mainHandler.post(runner)
    }

    override fun onPause() {
        super.onPause()
//        mainHandler.removeCallbacks(runner)
    }
}