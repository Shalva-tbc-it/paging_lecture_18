package com.example.paging.network.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.paging.network.UserPagingSource

class UserDataSourceFactory() {

    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }

    fun getUsers() =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UserPagingSource() }).flow

}