package com.alperozturk.ilovemovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.alperozturk.ilovemovies.model.response.PopularMoviesM
import com.alperozturk.ilovemovies.repository.MovieRepository

class PopularMoviesVM(private val repository: MovieRepository) : ViewModel() {

    private val _movieList = MutableLiveData<PagingData<PopularMoviesM>>()

    suspend fun getPopularMoviesList(): LiveData<PagingData<PopularMoviesM>> {
        val response = repository.getPopularMovieList().cachedIn(viewModelScope)
        _movieList.postValue(response.value)
        return response
    }

}