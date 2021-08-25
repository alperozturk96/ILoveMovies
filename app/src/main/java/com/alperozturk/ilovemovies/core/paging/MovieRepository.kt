package com.alperozturk.ilovemovies.core.paging

import androidx.lifecycle.LiveData;
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData;
import androidx.paging.liveData

import com.alperozturk.ilovemovies.models.response.PopularMoviesM;
import com.alperozturk.ilovemovies.networklayer.IRest;

interface MovieRepository {
    suspend fun getPopularMovieList(): LiveData<PagingData<PopularMoviesM>>
}

class MovieRepositoryImpl(private val service:IRest) : MovieRepository {
    override suspend fun getPopularMovieList(): LiveData<PagingData<PopularMoviesM>>
    {
        return Pager(config = PagingConfig(pageSize = 1,enablePlaceholders = false),
            pagingSourceFactory = {MoviePagingSource(service)}).liveData
    }
}
