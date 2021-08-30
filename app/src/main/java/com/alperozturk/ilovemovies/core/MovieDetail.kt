package com.alperozturk.ilovemovies.core

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleObserver
import androidx.recyclerview.widget.LinearLayoutManager
import com.alperozturk.ilovemovies.adapters.CreditsAdapter
import com.alperozturk.ilovemovies.databinding.MovieDetailFragmentBinding
import com.alperozturk.ilovemovies.helpers.AppConsts
import com.alperozturk.ilovemovies.helpers.Coroutines
import com.alperozturk.ilovemovies.models.response.MovieCreditsBaseM
import com.alperozturk.ilovemovies.models.response.MovieDetailBaseM
import com.alperozturk.ilovemovies.networklayer.Success
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException

class MovieDetail : BaseFragment<MovieDetailFragmentBinding>(MovieDetailFragmentBinding::inflate),LifecycleObserver {

    private lateinit var movieId:String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareMovieDetail()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieId = requireArguments().getString("movieId")!!
    }

    //While fetching movie detail there is no error handling, i don't like existed solution on the internet
    //Error handling can be using some helper classes or basically using try catch but its not the optimal solution.
    private fun prepareMovieDetail(){
        Coroutines.main {
            fetchMovieDetail()
            fetchMovieCredits()
        }
    }

    private suspend fun fetchMovieCredits(){
        val response = retroFitClient().getMovieCredits(movieId)

        val adapterForCast = CreditsAdapter(response,true)
        val adapterForCrew = CreditsAdapter(response,false)

        binding.recVCast.adapter = adapterForCast
        binding.recVCrew.adapter = adapterForCrew
    }

    private suspend fun fetchMovieDetail(){
        val response = retroFitClient().getMovieDetail(movieId)

        binding.movieTitle.text = response.title
        binding.movieDescription.text = response.overview
        Glide.with(requireContext())
            .load(AppConsts.imageBaseUrl + response.posterPath)
            .override(200,250)
            .into(binding.movieThumbnail)
    }


}