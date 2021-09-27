package com.example.jetpack.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack.datamodels.ExpandedListItem
import com.example.jetpack.datamodels.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(appObj: Application) : AndroidViewModel(appObj) {
    private val _users = MutableStateFlow(listOf<ExpandedListItem>())
    val users: StateFlow<List<ExpandedListItem>> get() = _users

    fun changeItemValue(index: Int, expanded: Boolean) {
        val list = _users.value.toMutableList()
        list[index] = list[index].copy(expanded = expanded)
        _users.value = list.toList()
    }
    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            val userList = arrayListOf(
                ExpandedListItem(
                    data = User(id = "1", name = "Ankit Singh", emailId = "abc@gmail.com"),
                    index = 0
                ),
                ExpandedListItem(
                    data = User(id = "2", name = "Neha Shaw", emailId = "xyz@gmail.com"),
                    index = 1
                ),
                ExpandedListItem(
                    data = User(id = "3", name = "Arpita Ghosh", emailId = "abc@gmail.com"),
                    index = 2
                ),
                ExpandedListItem(
                    data = User(id = "4", name = "Akash Tiwari", emailId = "xyz@gmail.com"),
                    index = 3
                )
            )
            _users.emit(userList)
        }
    }
}