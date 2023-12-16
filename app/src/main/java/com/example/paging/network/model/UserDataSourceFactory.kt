package com.example.paging.network.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.paging.network.DataRepository
import com.example.paging.network.UserPagingSource
import com.example.paging.network.api.ApiService

class UserDataSourceFactory(private val dataRepository: DataRepository) {

    companion object {
      const val NETWORK_PAGE_SIZE = 10
    }

    fun getPassengers() = Pager(config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { UserPagingSource(dataRepository) }).flow
}