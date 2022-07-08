package com.example.newsapp.di

import android.content.Context
import com.example.newsapp.R
import com.example.newsapp.services.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Provides Network instances of injected objects
 * [HttpLoggingInterceptor] & [OkHttpClient] &
 * [GsonConverterFactory] & [RxJava2CallAdapterFactory] &
 * [Retrofit] & [NewsService]
 * Created by Abanoub on 7/7/2022
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /*** provide domain name from [strings.xml] */
    @Provides
    fun provideDomainName(context: Context): String = context.getString(R.string.domain_name)

    /***
     * We use [HttpLoggingInterceptor] for DEBUG Logging
     * remove it when release an APK
     */
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRxJavaAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        domain: String,
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory,
        adapterFactory: RxJava2CallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(domain)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(adapterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsService(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }
}