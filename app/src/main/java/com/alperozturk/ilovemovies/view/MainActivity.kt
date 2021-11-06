package com.alperozturk.ilovemovies.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alperozturk.ilovemovies.R
import com.alperozturk.ilovemovies.utils.manager.ProgressBarManager


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initProgressBar()
        setContentView(R.layout.activity_main)
    }

    private fun initProgressBar() {
        val progressBarManager = ProgressBarManager()
        progressBarManager.initProgressBar(this)
    }
}