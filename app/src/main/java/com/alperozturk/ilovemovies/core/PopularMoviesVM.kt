package com.alperozturk.ilovemovies.core

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.alperozturk.ilovemovies.core.paging.MovieRepository
import com.alperozturk.ilovemovies.models.response.PopularMoviesM

class PopularMoviesVM(private val repository: MovieRepository) : ViewModel() {

    private val _movieList = MutableLiveData<PagingData<PopularMoviesM>>()

    suspend fun getPopularMoviesList(): LiveData<PagingData<PopularMoviesM>> {
        val response = repository.getPopularMovieList().cachedIn(viewModelScope)
        _movieList.value = response.value
        return response
    }
}