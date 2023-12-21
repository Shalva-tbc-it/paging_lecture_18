package com.example.paging.network.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.paging.network.model.User
import com.example.paging.network.model.UserDataSourceFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ApiViewModel() : ViewModel() {

    private val _userData = MutableStateFlow<PagingData<User>>(PagingData.empty())
    val userData: StateFlow<PagingData<User>> = _userData

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            UserDataSourceFactory().getUsers().collectLatest { pagingData ->
                _userData.value = pagingData
            }
        }
    }
}
