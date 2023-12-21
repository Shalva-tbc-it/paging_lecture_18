package com.example.paging.network.api

import com.example.paging.network.model.UserListResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("total_pages") loadSize: Int
    ): UserListResponse
}
