package com.example.paging.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging.network.model.Resource
import com.example.paging.network.model.User

class UserPagingSource(private val dataRepository: DataRepository) : PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {

        val position = params.key ?: 1

        return when (val result = dataRepository.getUsers(position, params.loadSize)) {

            is Resource.Failure -> {
                LoadResult.Error(Exception(result.toString()))
            }

            is Resource.Loading -> {
                LoadResult.Error(Exception())
            }

            is Resource.Success -> {
                val userListResponse = result.data

                if (userListResponse.userList.isNotEmpty() && position <= userListResponse.totalPages) {
                    LoadResult.Page(
                        data = userListResponse.userList,
                        prevKey = if (position == 1) null else position - 1,
                        nextKey = if (userListResponse.userList.isEmpty()) null else position + 1
                    )
                } else {
                    LoadResult.Error(Exception("Invalid data"))
                }
            }
        }
    }
}
