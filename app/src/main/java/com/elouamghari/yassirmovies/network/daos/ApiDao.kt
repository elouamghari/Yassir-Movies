package com.elouamghari.yassirmovies.network.daos

import com.elouamghari.yassirmovies.network.models.MovieDetails
import com.elouamghari.yassirmovies.network.responses.DiscoverResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiDao {

    @GET("discover/movie")
    fun getTrendingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): Observable<DiscoverResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Observable<MovieDetails>

}