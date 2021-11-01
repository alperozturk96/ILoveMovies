package com.alperozturk.ilovemovies.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alperozturk.ilovemovies.models.response.MovieCreditsBaseM
import com.alperozturk.ilovemovies.models.response.MovieDetailBaseM
import com.alperozturk.ilovemovies.networklayer.IRest
import com.alperozturk.ilovemovies.networklayer.ResultWrapper

import com.alperozturk.ilovemovies.repositories.MovieDetailRepository


class MovieDetailVM(service:IRest) : ViewModel() {

    private val repository = MovieDetailRepository(service)

    fun movieDetail(movieId: String): MutableLiveData<ResultWrapper<MovieDetailBaseM>> {
        return repository.getMovieDetail(movieId)
    }

    fun movieCredits(movieId: String): MutableLiveData<ResultWrapper<MovieCreditsBaseM>> {
        return repository.getMovieCredits(movieId)
    }

    fun disposeAll(){
        repository.finish()
    }

}