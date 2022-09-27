package com.elouamghari.yassirmovies.network.models

import com.google.gson.annotations.SerializedName

data class MovieDetails(
    @SerializedName("id") val id: Int,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("overview") val overview: String?,

    @SerializedName("genres") val genres: List<Genre>
){

    fun genresString(): String{
        var str = ""
        for (genre in genres){
            str += if(str.isEmpty()) genre.name else " - ${genre.name}"
        }
        return str
    }

}
