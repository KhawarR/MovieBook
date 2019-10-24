package com.pingo.moviebook.app.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pingo.moviebook.R
import com.pingo.moviebook.shared.ext.setFullscreen

/**
 * Activity container for [HomeFragment] to show all popular movies
 */
class HomeActivity : AppCompatActivity(R.layout.activity_fragment_template) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullscreen()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(
                R.id.fragment_container,
                HomeFragment.newInstance()
            ).commit()
        }
    }
}
