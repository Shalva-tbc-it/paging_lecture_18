package com.example.paging.network.model


sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data object Loading : Resource<Nothing>()
    data object Failure : Resource<Nothing>()
}


//sealed class Resource {
//    val data:UserListResponse = UserListResponse(100, 50, listOf<User>())
//    class Failure : Resource()
//    class Loading : Resource()
//    class Success : Resource()
//}