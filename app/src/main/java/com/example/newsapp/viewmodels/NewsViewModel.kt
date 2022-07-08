package com.example.newsapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.models.NewsResponse
import com.example.newsapp.repos.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * VIEW-MODEL LAYER
 * This class handle the data that coming from the server [NewsRepository]
 * and send it to the view MainActivity
 * Created by abanoub on 7/7/2022
 */
@HiltViewModel
class NewsViewModel
@Inject constructor(private val repository: NewsRepository): ViewModel() {

    val newsLiveData: MutableLiveData<NewsResponse> = MutableLiveData<NewsResponse>()
    val errorLiveData: MutableLiveData<String> = MutableLiveData<String>()
    private lateinit var disposable: Disposable

    fun onLoadAllNews(){
        repository.getNewsData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getMoviesListObserver())
    }

    private fun getMoviesListObserver(): Observer<NewsResponse> {
        return object : Observer<NewsResponse> {
            override fun onComplete() {
                //hide progress indicator .
            }

            override fun onError(e: Throwable) {
                errorLiveData.postValue(e.message)
                newsLiveData.postValue(null)

            }

            override fun onNext(t: NewsResponse) {
                newsLiveData.postValue(t)
            }

            override fun onSubscribe(d: Disposable) {
                disposable = d
                //start showing progress indicator.
            }
        }
    }
}