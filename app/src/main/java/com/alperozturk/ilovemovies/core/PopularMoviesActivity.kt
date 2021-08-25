package com.alperozturk.ilovemovies.core

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.alperozturk.ilovemovies.adapters.PopularMoviesAdapter
import com.alperozturk.ilovemovies.databinding.ActivityHomeBinding
import com.alperozturk.ilovemovies.models.response.PopularMoviesBaseM
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alperozturk.ilovemovies.core.paging.MovieRepository
import com.alperozturk.ilovemovies.core.paging.MovieRepositoryImpl
import com.alperozturk.ilovemovies.helpers.Coroutines
import kotlinx.coroutines.launch


class PopularMoviesActivity : BaseActivity<ActivityHomeBinding>() {

    private val viewModel: PopularMoviesVM by lazy {
        ViewModelProvider(this, createWithFactory { PopularMoviesVM(repository = MovieRepositoryImpl(retroFitClient())) }).get(PopularMoviesVM::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = PopularMoviesAdapter()

        binding.popularMovieListRecV.adapter = adapter

        binding.popularMovieListRecV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        Coroutines.main {
            viewModel.getPopularMoviesList().observe(this, {
                adapter.submitData(lifecycle, it)
            })
        }

    }

    override fun getViewBinding() = ActivityHomeBinding.inflate(layoutInflater)
}