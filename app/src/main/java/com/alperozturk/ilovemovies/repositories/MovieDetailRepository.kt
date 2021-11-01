package com.alperozturk.ilovemovies.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.alperozturk.ilovemovies.models.response.MovieCreditsBaseM
import com.alperozturk.ilovemovies.networklayer.ResultWrapper

import io.reactivex.rxjava3.schedulers.Schedulers

import com.alperozturk.ilovemovies.models.response.MovieDetailBaseM
import com.alperozturk.ilovemovies.networklayer.IRest
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers


class MovieDetailRepository(private val service: IRest) {

    fun getMovieDetail(movieId: String): MutableLiveData<ResultWrapper<MovieDetailBaseM>> {
        val liveData = MutableLiveData<ResultWrapper<MovieDetailBaseM>>()

        service.getMovieDetail(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response -> response }
            .subscribe({ result -> liveData.postValue(ResultWrapper.Success(result)) }
            ) { error ->
                liveData.value = ResultWrapper.GenericError(error)
                Log.e("", "error caught at getMovieDetail: $error")
            }

        return liveData
    }

    fun getMovieCredits(movieId: String): MutableLiveData<ResultWrapper<MovieCreditsBaseM>> {
        val liveData = MutableLiveData<ResultWrapper<MovieCreditsBaseM>>()

        service.getMovieCredits(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response -> response }
            .subscribe({ result -> liveData.postValue(ResultWrapper.Success(result)) }
            ) { error ->
                liveData.value = ResultWrapper.GenericError(error)
                Log.e("", "error caught at getMovieCredits: $error")
            }

        return liveData
    }
}