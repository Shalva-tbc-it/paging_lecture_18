package com.example.paging.network.model


sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data object Loading : Resource<Nothing>()
    data object Failure : Resource<Nothing>()
}