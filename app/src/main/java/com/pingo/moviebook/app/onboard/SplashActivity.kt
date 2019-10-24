package com.pingo.moviebook.app.onboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pingo.moviebook.app.home.HomeActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A Simple Splash screen showing app logo , with the delay of 1 second.
 * The Delay is just a template, here we can actually perform some operation at launch if necessary.
 */
class SplashActivity : AppCompatActivity() {

    private val startDelay = 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Wait for [startDelay] milliseconds in the background and then go to [HomeActivity]
         */
        GlobalScope.launch {
            delay(startDelay)
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, 0)
            finish()
        }

    }

}