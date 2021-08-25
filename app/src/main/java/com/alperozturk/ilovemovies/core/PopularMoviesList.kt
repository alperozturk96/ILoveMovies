package com.alperozturk.ilovemovies.core

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.alperozturk.ilovemovies.R
import com.alperozturk.ilovemovies.adapters.PopularMoviesAdapter
import com.alperozturk.ilovemovies.models.viewmodels.PopularMoviesVM
import com.alperozturk.ilovemovies.core.paging.MovieRepositoryImpl
import com.alperozturk.ilovemovies.databinding.PopularMovieListFragmentBinding
import com.alperozturk.ilovemovies.helpers.Coroutines


class PopularMoviesList : BaseFragment<PopularMovieListFragmentBinding>(PopularMovieListFragmentBinding::inflate),View.OnClickListener{

    var navController:NavController? = null

    private val viewModel: PopularMoviesVM by lazy {
        ViewModelProvider(this, createWithFactory { PopularMoviesVM(repository = MovieRepositoryImpl(retroFitClient())) }).get(
            PopularMoviesVM::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.headerTitle.setOnClickListener(this)
        preparePopularMovieList()
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.header_title -> navController!!.navigate(R.id.action_moviePList_to_movieDetail)
        }
    }

    private fun preparePopularMovieList(){
        val adapter = PopularMoviesAdapter()
        binding.popularMovieListRecV.adapter = adapter

        binding.popularMovieListRecV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        Coroutines.main {
            viewModel.getPopularMoviesList().observe(viewLifecycleOwner, {
                adapter.submitData(lifecycle, it)
            })
        }
    }

}