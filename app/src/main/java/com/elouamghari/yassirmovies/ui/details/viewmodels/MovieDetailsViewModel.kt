package com.elouamghari.yassirmovies.ui.details.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elouamghari.yassirmovies.network.Api
import com.elouamghari.yassirmovies.network.clients.ApiClient
import com.elouamghari.yassirmovies.network.constants.ApiConstants
import com.elouamghari.yassirmovies.network.models.MovieDetails
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class MovieDetailsViewModel : ViewModel() {

    private val movieMutable : MutableLiveData<MovieDetails> = MutableLiveData()

    val movie : LiveData<MovieDetails> by lazy { movieMutable }

    fun loadDetails(id: Int){
        Api.call(ApiClient.apiDao.getMovieDetails(id, ApiConstants.API_KEY), object : Observer<MovieDetails>{

            override fun onNext(movieDetails: MovieDetails) {
                movieMutable.value = movieDetails
            }

            override fun onSubscribe(d: Disposable) {}
            override fun onError(e: Throwable) {}
            override fun onComplete() {}
        })
    }

}