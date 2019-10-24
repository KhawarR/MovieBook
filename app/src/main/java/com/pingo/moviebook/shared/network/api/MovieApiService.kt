package com.pingo.moviebook.shared.network.api


import com.pingo.moviebook.shared.models.GenreList
import com.pingo.moviebook.shared.models.MovieObj
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**

 * EndPoint to fetch all Movie Lists
 */
interface MovieApiService {

    @GET("movie/popular")
    suspend fun getPopularMovieList(@Query("page") pageIndex: Int): Response<MovieObj>

    @GET("genre/movie/list")
    suspend fun getGenresList(): Response<GenreList>

    @GET("movie/popular")
    suspend fun getWithGenresMovieList(@Query("page") pageIndex: Int, @Query("with_genres") genreId: Int): Response<MovieObj>
}