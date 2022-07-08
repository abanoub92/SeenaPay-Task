package com.example.newsapp.services

import com.example.newsapp.models.NewsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Services APIs, this interface store all APIs from / to data server
 * Created by Abanoub on 7/7/2022
 */
interface NewsService {

    @GET("svc/topstories/v2/{section}.json")
    fun onNewsDataLoaded(
        @Path("section") section :String,
        @Query("api-key") key: String
    ): Observable<NewsResponse>

}