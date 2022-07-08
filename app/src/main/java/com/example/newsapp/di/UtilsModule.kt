package com.example.newsapp.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Provide global instance of some different objects
 * [ApplicationContext] & [Glide]
 * Created by Abanoub on 7/7/2022
 */
@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Singleton
    @Provides
    fun providesAppContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Singleton
    @Provides
    fun providesGlide(context: Context): RequestManager {
        return Glide.with(context)
    }
}