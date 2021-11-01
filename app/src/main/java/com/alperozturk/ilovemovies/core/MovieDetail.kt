package com.alperozturk.ilovemovies.core

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider
import com.alperozturk.ilovemovies.adapters.CreditsAdapter
import com.alperozturk.ilovemovies.databinding.MovieDetailFragmentBinding
import com.alperozturk.ilovemovies.helpers.AppConsts
import com.alperozturk.ilovemovies.helpers.Coroutines
import com.alperozturk.ilovemovies.models.response.MovieCreditsBaseM
import com.alperozturk.ilovemovies.models.response.MovieDetailBaseM
import com.alperozturk.ilovemovies.networklayer.APICall
import com.alperozturk.ilovemovies.networklayer.ResultWrapper
import com.alperozturk.ilovemovies.viewmodels.MovieDetailVM

import com.bumptech.glide.Glide


class MovieDetail : BaseFragment<MovieDetailFragmentBinding>(MovieDetailFragmentBinding::inflate),LifecycleObserver {

    private lateinit var movieId:String

    private val viewModel: MovieDetailVM by lazy {
        ViewModelProvider(
            this,
            createWithFactory { MovieDetailVM(retroFitClient()) })[MovieDetailVM::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieId = requireArguments().getString("movieId")!!
    }

    private fun observeLiveData(){
        viewModel.movieDetail(movieId).observe(viewLifecycleOwner, {
            when(it) {
                is ResultWrapper.Success -> {
                    prepareMovieDetails(it.value)
                }
                is ResultWrapper.GenericError -> {
                    Log.d("","error caught at prepareMovieDetails: ${it.error}")
                }
            }
        })

        viewModel.movieCredits(movieId).observe(viewLifecycleOwner, {
            when(it) {
                is ResultWrapper.Success -> {
                    prepareMovieCredits(it.value)
                }
                is ResultWrapper.GenericError -> {
                    Log.d("","error caught at prepareMovieCredits: ${it.error}")
                }
            }
        })
    }

    private fun prepareMovieCredits(movieCredits: MovieCreditsBaseM){
        val adapterForCast = CreditsAdapter(movieCredits,true)
        val adapterForCrew = CreditsAdapter(movieCredits,false)

        binding.recVCast.adapter = adapterForCast
        binding.recVCrew.adapter = adapterForCrew
    }

    private fun prepareMovieDetails(movieDetail:MovieDetailBaseM){
        binding.movieTitle.text = movieDetail.title
        binding.movieDescription.text = movieDetail.overview
        Glide.with(requireContext())
            .load(AppConsts.imageBaseUrl + movieDetail.posterPath)
            .override(200,250)
            .into(binding.movieThumbnail)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposeAll()
    }

}