package com.alperozturk.ilovemovies.view

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alperozturk.ilovemovies.R


//In this project i am using Single Activity Design Pattern therefore there is only one activity.
//Navigation UI library provides to manage all of fragments easily.
class MainActivity : AppCompatActivity() {
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initProgressBar()
        setContentView(R.layout.activity_main)
    }

    private fun initProgressBar() {
        progressBar = Dialog(this)
        progressBar?.setContentView(R.layout.progress_alert)
    }
}