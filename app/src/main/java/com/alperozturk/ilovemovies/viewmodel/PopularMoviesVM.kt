package com.alperozturk.ilovemovies.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.alperozturk.ilovemovies.repository.MovieRepository
import com.alperozturk.ilovemovies.model.response.PopularMoviesM

class PopularMoviesVM(private val repository: MovieRepository) : ViewModel() {

    private val _movieList = MutableLiveData<PagingData<PopularMoviesM>>()

    suspend fun getPopularMoviesList(): LiveData<PagingData<PopularMoviesM>> {
        val response = repository.getPopularMovieList().cachedIn(viewModelScope)
        _movieList.postValue(response.value)
        return response
    }
}