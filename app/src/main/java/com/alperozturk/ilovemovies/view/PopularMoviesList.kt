package com.alperozturk.ilovemovies.view

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alperozturk.ilovemovies.R
import com.alperozturk.ilovemovies.adapters.PopularMoviesAdapter
import com.alperozturk.ilovemovies.databinding.PopularMovieListFragmentBinding
import com.alperozturk.ilovemovies.repository.MovieRepositoryImpl
import com.alperozturk.ilovemovies.utils.Coroutines
import com.alperozturk.ilovemovies.viewmodel.PopularMoviesVM


class PopularMoviesList : BaseFragment<PopularMovieListFragmentBinding,PopularMoviesVM>(PopularMovieListFragmentBinding::inflate),
    SwipeRefreshLayout.OnRefreshListener{

    private var navController:NavController? = null
    private val adapter = PopularMoviesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        initOnClickListeners()
        initAdapter()
        observeLiveData()
    }

    override fun getVM(): PopularMoviesVM {
        return ViewModelProvider(this, createWithFactory { PopularMoviesVM(repository = MovieRepositoryImpl(service())) }).get(
            PopularMoviesVM::class.java)
    }

    private fun initOnClickListeners(){
        binding.refreshLayout.setOnRefreshListener(this);
    }

    private fun openMovieDetailPage(id:String){
        val bundle = bundleOf("movieId" to id)
        navController?.navigate(R.id.action_moviePList_to_movieDetail,bundle)
    }

    private fun initAdapter(){
        binding.popularMovieListRecV.adapter = adapter

        adapter.onItemClick = { data ->
            openMovieDetailPage(data.id.toString())
        }
    }

    private fun observeLiveData(){
        Coroutines.main {
            viewModel.getPopularMoviesList().observe(viewLifecycleOwner, {
                adapter.submitData(lifecycle, it)
            })
        }
    }

    override fun onRefresh() {
        observeLiveData()
        binding.refreshLayout.isRefreshing = false
    }

}