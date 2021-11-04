package com.alperozturk.ilovemovies.networklayer


import com.alperozturk.ilovemovies.helpers.AppConsts
import com.alperozturk.ilovemovies.models.response.MovieCreditsBaseM
import com.alperozturk.ilovemovies.models.response.MovieDetailBaseM
import com.alperozturk.ilovemovies.models.response.PopularMoviesBaseM
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface IRest {

    @GET("movie/popular?api_key="+AppConsts.apiKey+"language=en-US")
    suspend fun getPopularMovies(@Query("page") page: String): PopularMoviesBaseM

    @GET("movie/{movie_id}?api_key="+AppConsts.apiKey+"language=en-US")
    fun getMovieDetail(@Path("movie_id") movieId: String): Single<MovieDetailBaseM>

    @GET("movie/{movie_id}/credits?api_key="+AppConsts.apiKey+"language=en-US")
    fun getMovieCredits(@Path("movie_id") movieId: String): Single<MovieCreditsBaseM>

}