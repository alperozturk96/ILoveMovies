package com.alperozturk.ilovemovies.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alperozturk.ilovemovies.models.response.PopularMoviesM
import com.alperozturk.ilovemovies.networklayer.IRest

class MoviePagingSource(private val service: IRest) : PagingSource<Int, PopularMoviesM>() {
    private fun toListResponse(json: List<PopularMoviesM>?): List<PopularMoviesM> {
        with(json) {
            return if (this?.isNotEmpty() == true) {
                this.map { toItemResponse(it) }
            } else {
                emptyList()
            }
        }
    }

    private fun toItemResponse(json: PopularMoviesM): PopularMoviesM {
        with(json) {
            return PopularMoviesM(
                id = id,
                title = title,
                overview = overview,
                popularity = popularity,
                voteCount = voteCount,
                posterPath = posterPath
            )
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMoviesM> {
        val page = params.key ?: 1

        return try {
            val jsonResponse = service.getPopularMovies(page = page.toString())
            val response = toListResponse(jsonResponse.results)
            LoadResult.Page(
                data = response,
                prevKey = null, // Only paging forward.
                nextKey = page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PopularMoviesM>): Int? {
        return state.anchorPosition?.let { state.closestItemToPosition(it)?.id }
    }

}