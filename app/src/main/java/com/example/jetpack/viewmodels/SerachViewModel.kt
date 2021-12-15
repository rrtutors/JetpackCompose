package com.example.jetpack.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack.widget.SearchData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList


class SearchViewModel(appObj: Application) : AndroidViewModel(appObj) {
    private val _searchList = MutableStateFlow(listOf<SearchData>())
    val list = arrayListOf(
        SearchData(
            name = "Ankit Singh", emailId = "an@gmail.com"
        ),
        SearchData(
            name = "Neha Shaw", emailId = "ne@gmail.com"
        ),
        SearchData(
            name = "Arpita Ghosh", emailId = "ar@gmail.com"
        ),
        SearchData(
            name = "Akash Tiwari", emailId = "ak@gmail.com"
        ),
        SearchData(
            name = "Anisha Tiwari", emailId = "an@gmail.com"
        ),
        SearchData(
            name = "Rowdy Rathore", emailId = "ro@gmail.com"
        ),
        SearchData(
            name = "Jit Singh", emailId = "ji@gmail.com"
        ),
        SearchData(
            name = "Pravin Raj", emailId = "pr@gmail.com"
        ),
        SearchData(
            name = "Sneha Rao", emailId = "sn@gmail.com"
        ),
        SearchData(
            name = "Ranjana Rathore", emailId = "ran@gmail.com"
        ),
        SearchData(
            "Kamala Rathore", emailId = "ka@gmail.com"
        )
    )
    val searchList: StateFlow<List<SearchData>> get() = _searchList

    fun searchedItems(searchedText: String) {
        if (searchedText.isNotEmpty()) {
            val resultList = ArrayList<SearchData>()
            for (data in list) {
                if (data.name?.lowercase(Locale.getDefault())
                        ?.contains(searchedText, ignoreCase = true) == true
                ) {
                    resultList.add(data)
                }
            }
            _searchList.value = resultList
        } else {
            _searchList.value = list
        }
    }

    init {
        fetchList()
    }

    private fun fetchList() {
        viewModelScope.launch {
            _searchList.emit(list)
        }
    }
}