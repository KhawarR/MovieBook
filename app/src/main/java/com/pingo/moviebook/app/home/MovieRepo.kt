package com.pingo.moviebook.app.home

import com.pingo.moviebook.shared.models.GenreList
import com.pingo.moviebook.shared.models.MovieObj
import com.pingo.moviebook.shared.network.api.MovieApiService
import retrofit2.Response


class MovieRepo(private val service: MovieApiService) {

    /**
     * Fetch popular movies
     * @return Response<List<MovieObj>>
     */
    suspend fun fetchPopularMovies(pageIndex: Int): Response<MovieObj> {
        return service.getPopularMovieList(pageIndex)
    }

    /**
     * Fetch genres list
     * @return Response<List<GenreList>>
     */
    suspend fun fetchGenresList(): Response<GenreList> {
        return service.getGenresList()
    }

    /**
     * Fetch movies by Genre
     * @return Response<List<MovieObj>>
     */
    suspend fun fetchMoviesByGenre(pageIndex: Int, genreId: Int): Response<MovieObj> {
        return service.getWithGenresMovieList(pageIndex, genreId)
    }
}