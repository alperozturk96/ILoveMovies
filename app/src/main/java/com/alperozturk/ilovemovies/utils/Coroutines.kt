package com.alperozturk.ilovemovies.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//Coroutines extensions usable for all over the project and its super simply, easy to use.
object Coroutines {

    //Main thread is responsible for updating UI data therefore there is not gonna be any laggy ui while fetching datas.
    fun main(work: suspend (() -> Unit)) {
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }
    }

    fun io(work: suspend (() -> Unit)){
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }
    }

}
