package com.alperozturk.ilovemovies.networklayer


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class GenericError(val error: Throwable): ResultWrapper<Nothing>()
}

object APICall {
    suspend fun <T> run(apiCall: suspend () -> T): ResultWrapper<T> {
        return withContext(Dispatchers.IO) {
            try
            {
                val result = apiCall.invoke()
                ResultWrapper.Success(result)
            }
            catch (throwable: Exception)
            {
                ResultWrapper.GenericError(throwable)
            }
        }
    }
}


