package com.example.newsapp.models

import com.google.gson.annotations.SerializedName

/**
 * created by Abanoub on 7/7/2022
 */
data class NewsResponse(

    @SerializedName("results")
    val result: List<NewsModel>

)
