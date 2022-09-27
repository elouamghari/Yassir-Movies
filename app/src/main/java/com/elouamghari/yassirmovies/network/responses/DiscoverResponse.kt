package com.elouamghari.yassirmovies.network.responses

import com.elouamghari.yassirmovies.network.models.Movie
import com.google.gson.annotations.SerializedName

data class DiscoverResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("results") val results: List<Movie>
)