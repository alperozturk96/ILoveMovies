package com.alperozturk.ilovemovies.view.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alperozturk.ilovemovies.model.response.PopularMoviesM
import com.alperozturk.ilovemovies.service.APICall
import com.alperozturk.ilovemovies.service.IRest
import com.alperozturk.ilovemovies.service.ResultWrapper

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

        return try
        {
            val jsonResponse = APICall.run {
                service.getPopularMovies(page = page.toString())
            }

            when (jsonResponse) {
                is ResultWrapper.Success -> {
                    val response = toListResponse( jsonResponse.value.results)
                    LoadResult.Page(
                        data = response,
                        prevKey = null, // Only paging forward.
                        nextKey = page + 1
                    )
                }
                is ResultWrapper.GenericError -> {
                    val exception = jsonResponse.error
                    LoadResult.Error(exception!!)
                }
            }
        }
        catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PopularMoviesM>): Int? {
        return state.anchorPosition?.let { state.closestItemToPosition(it)?.id }
    }

}