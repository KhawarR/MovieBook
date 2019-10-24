package com.pingo.moviebook.app

import android.app.Application
import com.pingo.moviebook.app.home.data.homeRepo
import com.pingo.moviebook.app.home.data.homeViewModel
import com.pingo.moviebook.shared.network.apiService
import com.pingo.moviebook.shared.network.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MovieApp)
            modules(listOf(homeViewModel, homeRepo, retrofitModule , apiService))
        }

    }

}
