package com.pingo.moviebook.app.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pingo.moviebook.shared.models.Genre
import com.pingo.moviebook.shared.models.GenreList
import com.pingo.moviebook.shared.models.MovieObj
import com.pingo.moviebook.shared.ui.BaseViewModel
import kotlinx.coroutines.launch


/**
 * Bridging [HomeFragment] and Data Source
 */
open class HomeViewModel(private val movieRepo: MovieRepo) : BaseViewModel() {

    val popMoviesList: MutableLiveData<MovieObj> = MutableLiveData()
    val genresList: MutableLiveData<GenreList> = MutableLiveData()

    /**
     * Fetching popMoviesList from TMDB
     */
    fun fetchPopularMovies(pageIndex: Int) {

        viewModelScope.launch {

            try {

                showProgress.postValue(true)

                val response = movieRepo.fetchPopularMovies(pageIndex)

                if (response.isSuccessful) {
                    showProgress.postValue(false)
                    popMoviesList.postValue(response.body())
                } else {
                    onBaseError(response.errorBody())
                }

            } catch (exp: Exception) {
                onBaseError(exp)
            }
        }
    }

    /**
     * Fetching genre list
     */
    fun fetchGenresList() {

        viewModelScope.launch {

            try {

                showProgress.postValue(true)

                val response = movieRepo.fetchGenresList()

                if (response.isSuccessful) {
                    showProgress.postValue(false)

                    val genresObj = response.body()
                    val genreListArr = genresObj?.genres as ArrayList<Genre>

                    val genreAll = Genre(-1, "Show All")
                    genreListArr.add(0, genreAll)

                    genresObj.genres = genreListArr

                    genresList.postValue(genresObj)
                } else {
                    onBaseError(response.errorBody())
                }

            } catch (exp: Exception) {
                onBaseError(exp)
            }
        }
    }

    /**
     * Fetching movies list by genre
     */
    fun fetchMoviesByGenre(pageIndex: Int, genreId: Int) {

        viewModelScope.launch {

            try {

                showProgress.postValue(true)

                val response = movieRepo.fetchMoviesByGenre(pageIndex, genreId)

                if (response.isSuccessful) {
                    showProgress.postValue(false)
                    popMoviesList.postValue(response.body())
                } else {
                    onBaseError(response.errorBody())
                }

            } catch (exp: Exception) {
                onBaseError(exp)
            }
        }
    }
}