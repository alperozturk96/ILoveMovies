package com.alperozturk.ilovemovies.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alperozturk.ilovemovies.model.response.MovieCreditsBaseM
import com.alperozturk.ilovemovies.model.response.MovieDetailBaseM
import com.alperozturk.ilovemovies.repository.MovieDetailRepository
import com.alperozturk.ilovemovies.service.IRest
import com.alperozturk.ilovemovies.service.ResultWrapper


class MovieDetailVM(service:IRest) : ViewModel() {

    var movieId = ""
    private val repository = MovieDetailRepository(service)

    fun movieDetail(): MutableLiveData<ResultWrapper<MovieDetailBaseM>> {
        return repository.getMovieDetail(movieId)
    }

    fun movieCredits(): MutableLiveData<ResultWrapper<MovieCreditsBaseM>> {
        return repository.getMovieCredits(movieId)
    }

    fun loadingIndicator():MutableLiveData<Boolean> {
        return repository.isLoading()
    }

    fun disposeAll(){
        repository.finish()
    }

}