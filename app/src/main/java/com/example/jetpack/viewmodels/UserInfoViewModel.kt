package com.example.jetpack.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jetpack.datamodels.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.awaitResponse


class UserInfoViewModel(appObj: Application) : AndroidViewModel(appObj) {
    val users: Flow<PagingData<UserInfo>> = Pager(PagingConfig(pageSize = 6)) {
        UserPagingSource()
    }.flow.cachedIn(viewModelScope)
}