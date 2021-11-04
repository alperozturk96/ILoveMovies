package com.alperozturk.ilovemovies.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.alperozturk.ilovemovies.service.ApiClient
import com.alperozturk.ilovemovies.service.IRest
import retrofit2.Retrofit


typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

//Due to using navigation ui library we need to have base abstract fragment for using viewbindings all over the app.
//And some needed functions all over the app.
abstract class BaseFragment<VB: ViewBinding,VM: ViewModel>(private val inflate: Inflate<VB>) : Fragment() {

    companion object {
        fun service(): IRest {
            val retrofit: Retrofit = ApiClient.client()
            return retrofit.create(IRest::class.java)
        }
    }

    lateinit var binding: VB
    lateinit var viewModel: VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getVM()
    }

    abstract fun getVM(): VM

    open fun createWithFactory(create: () -> ViewModel): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return create.invoke() as T
            }
        }
    }

}
