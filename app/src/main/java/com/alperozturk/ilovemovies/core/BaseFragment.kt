package com.alperozturk.ilovemovies.core

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.alperozturk.ilovemovies.networklayer.ApiClient
import com.alperozturk.ilovemovies.networklayer.IRest
import retrofit2.Retrofit
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.functions.Consumer


typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

//Due to using navigation ui library we need to have base abstract fragment for using viewbindings all over the app.
//And some needed functions all over the app.

abstract class BaseFragment<VB: ViewBinding>(private val inflate: Inflate<VB>) : Fragment() {

    companion object {
        var progressBar: Dialog? = null

        fun showProgressBar() {
            if (progressBar != null && !progressBar!!.isShowing) {
                progressBar!!.show()
            }
        }

        fun hideProgressBar() {
            if (progressBar != null && progressBar!!.isShowing) {
                progressBar!!.dismiss()
            }
        }

        fun retroFitClient(): IRest {
            val retrofit: Retrofit = ApiClient.client()
            return retrofit.create(IRest::class.java)
        }
    }

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    open fun createWithFactory(create: () -> ViewModel): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return create.invoke() as T
            }
        }
    }

}
