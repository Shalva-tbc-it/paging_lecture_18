package com.example.paging.network

import com.example.paging.network.api.ApiService
import com.example.paging.network.model.Resource
import com.example.paging.network.model.UserListResponse


class DataRepository(private val apiService: ApiService) {
    suspend fun getUsers(page: Int, pageSize: Int): Resource<UserListResponse> {
        return try {
            val response = apiService.getUsers(page, pageSize)
            if (response.userList.isNotEmpty()) {
                Resource.Success(response)
            } else {
                Resource.Failure
            }
        } catch (e: Exception) {
            Resource.Failure
        }
    }
}
