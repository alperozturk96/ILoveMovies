package com.alperozturk.ilovemovies.utils

import androidx.recyclerview.widget.RecyclerView

//This extension provide to implement onClickListener easily
object ViewHolder {
    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(bindingAdapterPosition, itemViewType)
        }
        return this
    }

}