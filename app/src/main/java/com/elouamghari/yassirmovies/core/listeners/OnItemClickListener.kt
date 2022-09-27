package com.elouamghari.yassirmovies.core.listeners

fun interface OnItemClickListener<T> {
    fun onItemClick(item: T)
}