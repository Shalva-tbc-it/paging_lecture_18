package com.example.paging.network.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paging.network.DataRepository
import com.example.paging.network.api.RetrofitInstance
import com.example.paging.network.model.Resource
import com.example.paging.network.model.UserListResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ApiViewModel() : ViewModel() {

    private val dataRepository = DataRepository(RetrofitInstance.apiService)
    private val _userData = MutableStateFlow<Resource<UserListResponse>>(Resource.Loading)
    val userData: StateFlow<Resource<UserListResponse>> get() = _userData

    fun fetchData(page: Int, pageSize: Int) {
        viewModelScope.launch {
            _userData.value = dataRepository.getUsers(page, pageSize)
        }
    }
}
//private val dataRepository: DataRepository
//val userData: Flow<PagingData<User>> = Pager(
//    config = PagingConfig(pageSize = UserDataSourceFactory.NETWORK_PAGE_SIZE, enablePlaceholders = false),
//    pagingSourceFactory = { UserPagingSource(dataRepository) }
//).flow.cachedIn(viewModelScope)