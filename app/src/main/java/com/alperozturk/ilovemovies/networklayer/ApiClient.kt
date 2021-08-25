package com.alperozturk.ilovemovies.networklayer

import com.alperozturk.ilovemovies.helpers.AppConsts
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

object ApiClient {
    private var retrofit: Retrofit? = null

    fun client(): Retrofit {
        if (retrofit == null)
            retrofit =
                Retrofit.Builder()
                    .baseUrl(AppConsts.baseUrl)
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .addConverterFactory(JacksonConverterFactory.create()).build()

        return retrofit as Retrofit
    }
}