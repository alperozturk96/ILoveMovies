package com.alperozturk.ilovemovies.networklayer

import com.alperozturk.ilovemovies.helpers.AppConsts
import com.alperozturk.ilovemovies.models.response.PopularMoviesBaseM
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IRest {

    @GET("movie/popular?api_key="+AppConsts.apiKey+"language=en-US")
    suspend fun getPopularMovies(@Query("page") page: String): PopularMoviesBaseM


}