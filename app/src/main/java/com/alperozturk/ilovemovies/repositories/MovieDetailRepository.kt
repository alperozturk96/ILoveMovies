package com.alperozturk.ilovemovies.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.alperozturk.ilovemovies.models.response.MovieCreditsBaseM
import com.alperozturk.ilovemovies.networklayer.ResultWrapper

import io.reactivex.rxjava3.schedulers.Schedulers

import com.alperozturk.ilovemovies.models.response.MovieDetailBaseM
import com.alperozturk.ilovemovies.networklayer.IRest
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable


class MovieDetailRepository(private val service: IRest) {

    var movieDetailDisposable: Disposable? = null
    var movieCreditsDisposable: Disposable? = null

    fun getMovieDetail(movieId: String): MutableLiveData<ResultWrapper<MovieDetailBaseM>> {
        val liveData = MutableLiveData<ResultWrapper<MovieDetailBaseM>>()

        movieDetailDisposable = service.getMovieDetail(movieId)
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

        movieCreditsDisposable = service.getMovieCredits(movieId)
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

    fun finish(){
        movieDetailDisposable?.dispose()
        movieCreditsDisposable?.dispose()
    }
}