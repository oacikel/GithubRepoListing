package com.ocul.githubrepolisting.view.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.navigation.ui.NavigationUI
import com.ocul.githubrepolisting.R
import androidx.navigation.findNavController
import com.ocul.githubrepolisting.repository.PrefRepository
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val LOG_TAG="OCULCAN - MainActivity"
    private lateinit var prefRepository: PrefRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefRepository= PrefRepository(this)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        NavigationUI.setupActionBarWithNavController(this, findNavController(R.id.main_nav_fragment))
        Log.d(LOG_TAG,"On Create - Favourite repo count is: "+prefRepository.getFavouriteIds()?.size)
    }

    override fun onSupportNavigateUp() = findNavController(R.id.main_nav_fragment).navigateUp()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.action_buttons, menu);
        return false;
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG,"On Pause - Favourite repo count is: "+prefRepository.getFavouriteIds()?.size)
    }

    override fun onDestroy() {
        Log.d(LOG_TAG,"On Destroy - Favourite repo count is: "+prefRepository.getFavouriteIds()?.size)
        super.onDestroy()
    }
}