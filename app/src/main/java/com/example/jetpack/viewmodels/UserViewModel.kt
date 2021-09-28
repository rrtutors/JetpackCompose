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
                    data = User(id = "1", name = "Ankit Singh", emailId = "an@gmail.com"),
                    index = 0
                ),
                ExpandedListItem(
                    data = User(id = "2", name = "Neha Shaw", emailId = "ne@gmail.com"),
                    index = 1
                ),
                ExpandedListItem(
                    data = User(id = "3", name = "Arpita Ghosh", emailId = "ar@gmail.com"),
                    index = 2
                ),
                ExpandedListItem(
                    data = User(id = "4", name = "Akash Tiwari", emailId = "ak@gmail.com"),
                    index = 3
                ),
                ExpandedListItem(
                    data = User(id = "5", name = "Anisha Tiwari", emailId = "an@gmail.com"),
                    index = 4
                ),
                ExpandedListItem(
                    data = User(id = "6", name = "Rowdy Rathore", emailId = "ro@gmail.com"),
                    index = 5
                ),
                ExpandedListItem(
                    data = User(id = "7", name = "Jit Singh", emailId = "ji@gmail.com"),
                    index = 6
                ),
                ExpandedListItem(
                    data = User(id = "8", name = "Pravin Raj", emailId = "pr@gmail.com"),
                    index = 7
                ),
                ExpandedListItem(
                    data = User(id = "9", name = "Sneha Rao", emailId = "sn@gmail.com"),
                    index = 8
                ),
                ExpandedListItem(
                    data = User(id = "10", name = "Ranjana Rathore", emailId = "ran@gmail.com"),
                    index = 9
                ),
                ExpandedListItem(
                    data = User(id = "11", name = "Kamala Rathore", emailId = "ka@gmail.com"),
                    index = 10
                ),
                ExpandedListItem(
                    data = User(id = "12", name = "Ranjani Rathore", emailId = "raj@gmail.com"),
                    index = 11
                ),
                ExpandedListItem(
                    data = User(id = "13", name = "XYZ Rathore", emailId = "xyz@gmail.com"),
                    index = 12
                ),
                ExpandedListItem(
                    data = User(id = "14", name = "ZAR Rathore", emailId = "zar@gmail.com"),
                    index = 13
                )
            )
            _users.emit(userList)
        }
    }
}