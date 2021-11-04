package com.alperozturk.ilovemovies.service

import com.alperozturk.ilovemovies.utils.AppConsts
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor

import okhttp3.OkHttpClient

import okhttp3.Interceptor
import okhttp3.Request
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import java.util.concurrent.TimeUnit
import retrofit2.converter.gson.GsonConverterFactory


//Object acts like singleton class and its suitable for Retrofit Networking Library.
object ApiClient {
    private var retrofit: Retrofit? = null

    //This functions provide to us watch network request from log.
    private fun httpClient(): OkHttpClient.Builder {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        httpClient.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val request: Request = chain.request().newBuilder()
                .build()
            chain.proceed(request)
        })
        return httpClient
    }

    fun client(): Retrofit {
        if (retrofit == null)
            retrofit =
                Retrofit.Builder()
                    .baseUrl(AppConsts.baseUrl)
                    .client(httpClient().build())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build()

        return retrofit as Retrofit
    }
}