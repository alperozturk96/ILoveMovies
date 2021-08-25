package com.alperozturk.ilovemovies.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.alperozturk.ilovemovies.networklayer.ApiClient
import com.alperozturk.ilovemovies.networklayer.IRest
import retrofit2.Retrofit

abstract class BaseActivity<B : ViewBinding>: AppCompatActivity() {

    protected lateinit var binding:B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding();
        setContentView(binding.root);
    }

    abstract fun getViewBinding(): B

    open fun retroFitClient(): IRest {
        val retrofit: Retrofit = ApiClient.client()
        return retrofit.create(IRest::class.java)
    }


    open fun createWithFactory(create: () -> ViewModel): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")// Casting T as ViewModel
                return create.invoke() as T
            }
        }
    }


}