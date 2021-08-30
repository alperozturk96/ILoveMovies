package com.alperozturk.ilovemovies.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alperozturk.ilovemovies.databinding.CreditsItemBinding
import com.alperozturk.ilovemovies.helpers.ViewHolder.listen
import com.alperozturk.ilovemovies.models.response.MovieCreditsBaseM

class CreditsAdapter(private var credits: MovieCreditsBaseM, private var isCast: Boolean) :
    RecyclerView.Adapter<CreditsAdapter.MyViewHolder>() {

    inner class MyViewHolder(val bindingCredits: CreditsItemBinding) :RecyclerView.ViewHolder(bindingCredits.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CreditsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (isCast) {
            credits.cast?.size!!
        } else {
            credits.crew?.size!!
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            if (isCast)
            {
                bindingCredits.knownFor.text = credits.cast?.get(position)!!.character + ": "
                bindingCredits.name.text = credits.cast?.get(position)!!.name
            }
            else
            {
                bindingCredits.knownFor.text = credits.crew?.get(position)!!.knownForDepartment + ": "
                bindingCredits.name.text = credits.crew?.get(position)!!.name
            }
        }

    }

}