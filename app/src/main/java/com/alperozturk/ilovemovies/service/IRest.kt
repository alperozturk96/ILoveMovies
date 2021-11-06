package com.alperozturk.ilovemovies.service


import com.alperozturk.ilovemovies.model.response.MovieCreditsBaseM
import com.alperozturk.ilovemovies.model.response.MovieDetailBaseM
import com.alperozturk.ilovemovies.model.response.PopularMoviesBaseM
import com.alperozturk.ilovemovies.utils.AppConsts
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IRest {

    @GET("movie/popular?api_key="+AppConsts.apiKey+"language=en-US")
    suspend fun getPopularMovies(@Query("page") page: String): PopularMoviesBaseM

    @GET("movie/{movie_id}?api_key="+AppConsts.apiKey+"language=en-US")
    fun getMovieDetail(@Path("movie_id") movieId: String): Single<MovieDetailBaseM>

    @GET("movie/{movie_id}/credits?api_key="+AppConsts.apiKey+"language=en-US")
    fun getMovieCredits(@Path("movie_id") movieId: String): Single<MovieCreditsBaseM>

}