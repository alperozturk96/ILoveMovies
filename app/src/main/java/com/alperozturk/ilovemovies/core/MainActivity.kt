package com.alperozturk.ilovemovies.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alperozturk.ilovemovies.R

//In this project i am using Single Activity Design Pattern therefore there is only one activity.
//Navigation UI library provides to manage all of fragments easily.
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}