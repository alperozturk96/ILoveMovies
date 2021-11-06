package com.alperozturk.ilovemovies.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.alperozturk.ilovemovies.model.response.PopularMoviesM
import com.alperozturk.ilovemovies.service.IRest
import com.alperozturk.ilovemovies.view.paging.MoviePagingSource


interface MovieRepository {
    suspend fun getPopularMovieList(): LiveData<PagingData<PopularMoviesM>>
}

class MovieRepositoryImpl(private val service:IRest) : MovieRepository {
    override suspend fun getPopularMovieList(): LiveData<PagingData<PopularMoviesM>>
    {
        return Pager(config = PagingConfig(pageSize = 1,enablePlaceholders = false),
            pagingSourceFactory = { MoviePagingSource(service) }).liveData
    }
}
