package com.alperozturk.ilovemovies.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alperozturk.ilovemovies.R
import com.alperozturk.ilovemovies.databinding.PopularMoviesListItemBinding
import com.alperozturk.ilovemovies.helpers.AppConsts
import com.alperozturk.ilovemovies.models.response.PopularMoviesBaseM
import com.alperozturk.ilovemovies.models.response.PopularMoviesM
import com.bumptech.glide.Glide

class PopularMoviesAdapter() : PagingDataAdapter<PopularMoviesM, PopularMoviesAdapter.ViewHolder>(DiffUtilCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PopularMoviesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(private val binding: PopularMoviesListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        @SuppressLint("SetTextI18n")
        fun bind(item: PopularMoviesM) {
            with(binding) {
                title.text = item.title
                voteCount.text = item.voteCount.toString()
                popularity.text = item.popularity.toString()
                overview.text = item.overview?.take(140) + " ..."

                Glide.with(context)
                    .load(AppConsts.imageBaseUrl + item.posterPath)
                    .override(200,250)
                    .into(thumbnail)
            }
        }
    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<PopularMoviesM>() {
        override fun areItemsTheSame(oldItem: PopularMoviesM, newItem: PopularMoviesM): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PopularMoviesM, newItem: PopularMoviesM): Boolean {
            return oldItem == newItem
        }
    }
}