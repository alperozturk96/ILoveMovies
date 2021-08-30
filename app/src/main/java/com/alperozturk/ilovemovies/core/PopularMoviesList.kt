package com.alperozturk.ilovemovies.core

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.alperozturk.ilovemovies.R
import com.alperozturk.ilovemovies.adapters.PopularMoviesAdapter
import com.alperozturk.ilovemovies.models.viewmodels.PopularMoviesVM
import com.alperozturk.ilovemovies.models.repositories.MovieRepositoryImpl
import com.alperozturk.ilovemovies.databinding.PopularMovieListFragmentBinding
import com.alperozturk.ilovemovies.helpers.Coroutines


class PopularMoviesList : BaseFragment<PopularMovieListFragmentBinding>(PopularMovieListFragmentBinding::inflate){

    var navController:NavController? = null

    private val viewModel: PopularMoviesVM by lazy {
        ViewModelProvider(this, createWithFactory { PopularMoviesVM(repository = MovieRepositoryImpl(retroFitClient())) }).get(
            PopularMoviesVM::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        preparePopularMovieList()
    }


    private fun openMovieDetailPage(id:String){
        val bundle = bundleOf("movieId" to id)
        navController!!.navigate(R.id.action_moviePList_to_movieDetail,bundle)
    }

    private fun preparePopularMovieList(){
        val adapter = PopularMoviesAdapter()
        binding.popularMovieListRecV.adapter = adapter

        adapter.onItemClick = { data ->
            openMovieDetailPage(data.id.toString())
        }

        binding.popularMovieListRecV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        Coroutines.main {
            viewModel.getPopularMoviesList().observe(viewLifecycleOwner, {
                adapter.submitData(lifecycle, it)
            })
        }
    }

}