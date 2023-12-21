package com.example.paging.network.view_model

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paging.network.UserPagingSource
import com.example.paging.network.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ApiViewModel() : ViewModel() {


    private val _userData = MutableStateFlow<PagingData<User>>(PagingData.empty())
    val userData: StateFlow<PagingData<User>> = _userData

    init {
        fetchData()
    }

    private fun fetchData() {
//        viewModelScope.launch {
//            userDataSourceFactory.getUsers().collectLatest { pagingData ->
//                _userData.value = pagingData
//            }
//        }

        Pager(
            config = PagingConfig(pageSize = 2),
            pagingSourceFactory = { UserPagingSource() }
        ).flow
            .cachedIn(viewModelScope)
            .onEach { pagingData ->
                d("pagingDataModel", "$pagingData")
                _userData.value = pagingData
            }
            .launchIn(viewModelScope)
    }
}