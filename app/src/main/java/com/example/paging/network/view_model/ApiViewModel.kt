package com.example.paging.network.view_model

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.paging.network.model.User
import com.example.paging.network.model.UserDataSourceFactory
import kotlinx.coroutines.flow.Flow

class ApiViewModel() : ViewModel() {



    private val userDataSourceFactory = UserDataSourceFactory()

    val usersPagingData: Flow<PagingData<User>> = userDataSourceFactory.getUsers()

}

//private val _userData = MutableStateFlow<PagingData<User>>(PagingData.empty())
//    val userData: MutableStateFlow<PagingData<User>> = _userData
//    init {
//        fetchData()
//    }
//
//    private fun fetchData() {
//        viewModelScope.launch {
//            try {
//                val response = RetrofitInstance.apiService.getUsers(1, 6)
//                val pagingData = PagingData.from(response.userList)
//
//                _userData.value = pagingData
//
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }


//class ApiViewModel(
//    private val dataRepository: DataRepository
//) : ViewModel() {
//
//    private val userDataSourceFactory = UserDataSourceFactory(dataRepository)
//
//    val usersPagingData: Flow<PagingData<User>> = userDataSourceFactory.getUsers()
//        .cachedIn(viewModelScope)
//}


//    val usersPagingData: Flow<PagingData<User>> by lazy {
//        UserDataSourceFactory(dataRepository)
//            .getUsers()
//            .cachedIn(viewModelScope)
//    }

//    private val dataRepository = DataRepository(RetrofitInstance.apiService)
//    private val _userData = MutableStateFlow<Resource<UserListResponse>>(Resource.Loading)
//    val userData: StateFlow<Resource<UserListResponse>> get() = _userData
//
//    fun fetchData(page: Int, pageSize: Int) {
//        viewModelScope.launch {
//            _userData.value = dataRepository.getUsers(page, pageSize)
//        }
//    }
//}

//private val dataRepository: DataRepository
//val userData: Flow<PagingData<User>> = Pager(
//    config = PagingConfig(pageSize = UserDataSourceFactory.NETWORK_PAGE_SIZE, enablePlaceholders = false),
//    pagingSourceFactory = { UserPagingSource(dataRepository) }
//).flow.cachedIn(viewModelScope)