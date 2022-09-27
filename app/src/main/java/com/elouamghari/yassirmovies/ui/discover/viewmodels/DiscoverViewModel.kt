package com.elouamghari.yassirmovies.ui.discover.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elouamghari.yassirmovies.network.Api
import com.elouamghari.yassirmovies.network.clients.ApiClient
import com.elouamghari.yassirmovies.network.constants.ApiConstants
import com.elouamghari.yassirmovies.network.models.Movie
import com.elouamghari.yassirmovies.network.responses.DiscoverResponse
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class DiscoverViewModel : ViewModel() {

    private var page: Int = 1
    private val moviesList: MutableList<Movie> = mutableListOf()
    private val moviesMutable: MutableLiveData<List<Movie>> = MutableLiveData(listOf())

    val movies: LiveData<List<Movie>> by lazy { moviesMutable }

    init {
        loadMovies()
    }

    private fun loadMovies(){
        Api.call(ApiClient.apiDao.getTrendingMovies(ApiConstants.API_KEY), object :
            Observer<DiscoverResponse> {
            override fun onNext(response: DiscoverResponse) {
                page = response.page+1
                moviesList.addAll(response.results)
                moviesMutable.value = moviesList
            }

            override fun onSubscribe(d: Disposable) {}
            override fun onError(e: Throwable) {}
            override fun onComplete() {}
        })
    }

    fun loadMore(){
        Api.call(ApiClient.apiDao.getTrendingMovies(ApiConstants.API_KEY, page), object :
            Observer<DiscoverResponse>{
            override fun onNext(response: DiscoverResponse) {
                page = response.page+1
                moviesList.addAll(response.results)
                moviesMutable.value = moviesList
            }

            override fun onSubscribe(d: Disposable) {}
            override fun onError(e: Throwable) {
            }
            override fun onComplete() {}
        })
    }

}