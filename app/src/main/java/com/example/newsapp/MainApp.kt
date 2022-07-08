package com.example.newsapp

import android.app.Application
import com.github.piasy.biv.BigImageViewer
import com.github.piasy.biv.loader.glide.GlideImageLoader
import dagger.hilt.android.HiltAndroidApp

/***
 * Main Application class injected with hilt
 * created by Abanoub on 7/7/2022
 */
@HiltAndroidApp
class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()

        /** Recommended to initialized with applicationContext */
        BigImageViewer.initialize(GlideImageLoader.with(applicationContext))
    }
}