package com.alperozturk.ilovemovies.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider
import com.alperozturk.ilovemovies.adapters.CreditsAdapter
import com.alperozturk.ilovemovies.view.MainActivity.Companion.hideProgressBar
import com.alperozturk.ilovemovies.view.MainActivity.Companion.showProgressBar
import com.alperozturk.ilovemovies.databinding.MovieDetailFragmentBinding
import com.alperozturk.ilovemovies.utils.AppConsts
import com.alperozturk.ilovemovies.model.response.MovieCreditsBaseM
import com.alperozturk.ilovemovies.model.response.MovieDetailBaseM
import com.alperozturk.ilovemovies.service.ResultWrapper
import com.alperozturk.ilovemovies.viewmodel.MovieDetailVM

import com.bumptech.glide.Glide


class MovieDetail : BaseFragment<MovieDetailFragmentBinding,MovieDetailVM>(MovieDetailFragmentBinding::inflate),LifecycleObserver {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null)
        {
            val movieId = requireArguments().getString("movieId") ?: return
            viewModel.movieId = movieId
        }
    }

    override fun getVM(): MovieDetailVM {
        return ViewModelProvider(this, createWithFactory { MovieDetailVM(service()) })[MovieDetailVM::class.java]
    }

    private fun observeLiveData(){
        viewModel.loadingIndicator().observe(viewLifecycleOwner,{
            if (it)
            {
                showProgressBar()
            }
            else
            {
                hideProgressBar()
            }
        })

        viewModel.movieDetail().observe(viewLifecycleOwner, {
            when(it) {
                is ResultWrapper.Success -> {
                    prepareMovieDetails(it.value)
                }
                is ResultWrapper.GenericError -> {
                    Log.d("","error caught at prepareMovieDetails: ${it.error}")
                }
            }
        })

        viewModel.movieCredits().observe(viewLifecycleOwner, {
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