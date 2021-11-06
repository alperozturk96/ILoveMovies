package com.alperozturk.ilovemovies.utils.manager

import android.app.Dialog
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.alperozturk.ilovemovies.R

class ProgressBarManager {

    companion object {
        var progressBar: Dialog? = null

        fun showProgressBar() {
            Handler(Looper.getMainLooper()).post {
                if (progressBar != null && !progressBar!!.isShowing) {
                    progressBar!!.show()
                }
            }
        }

        fun hideProgressBar() {
            Handler(Looper.getMainLooper()).post {
                if (progressBar != null && progressBar!!.isShowing) {
                    progressBar!!.dismiss()
                }
            }
        }
    }

    fun initProgressBar(context:Context) {
        progressBar = Dialog(context)
        progressBar?.setContentView(R.layout.progress_alert)
    }
}