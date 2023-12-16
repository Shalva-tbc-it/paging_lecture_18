package com.example.paging.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging.network.model.Resource
import com.example.paging.network.model.User

class UserPagingSource(private val dataRepository: DataRepository):PagingSource<Int, User>() {
    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return when (val result = dataRepository.getUsers(position, params.loadSize)) {
            is Resource.Failure -> {
                LoadResult.Error(Exception(result.toString()))
            }
            is Resource.Loading -> {
                LoadResult.Error(Exception())
            }
            is Resource.Success -> {
                val userListResponse = result.data
                LoadResult.Page(
                    data = userListResponse.userList,
                    prevKey = if (position == STARTING_PAGE_INDEX) null else -1,
                    nextKey = if (userListResponse.userList.isEmpty()) null else position + 1
                )
            }
        }
    }
}
