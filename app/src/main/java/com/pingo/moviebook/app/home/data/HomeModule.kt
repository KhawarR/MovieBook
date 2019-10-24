package com.pingo.moviebook.app.home.data

import com.pingo.moviebook.app.home.MovieRepo
import com.pingo.moviebook.app.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



val homeViewModel = module {
    viewModel { HomeViewModel(get()) }
}

val homeRepo = module {
    single { MovieRepo(get()) }
}

