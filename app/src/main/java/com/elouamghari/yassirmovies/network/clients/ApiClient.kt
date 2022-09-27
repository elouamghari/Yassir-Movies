package com.elouamghari.yassirmovies.network.clients

import com.elouamghari.yassirmovies.network.constants.ApiConstants
import com.elouamghari.yassirmovies.network.daos.ApiDao
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val apiClient: Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(ApiConstants.API_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiDao: ApiDao by lazy {
        apiClient.create(ApiDao::class.java)
    }

}