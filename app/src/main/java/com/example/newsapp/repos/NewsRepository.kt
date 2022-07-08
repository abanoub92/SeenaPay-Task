package com.example.newsapp.repos

import android.content.Context
import com.example.newsapp.R
import com.example.newsapp.services.NewsService
import javax.inject.Inject

/**
 * REPOSITORY LAYER
 * This layer handle the process of connecting
 * to the server [NewsService] and fetching data
 * Created by Abanoub on 7/7/2022
 */
class NewsRepository
@Inject
constructor(private val context: Context, private val service: NewsService) {

    fun getNewsData() = service.onNewsDataLoaded(
        section = "arts",
        key = context.getString(R.string.api_key)
    )

}