package com.example.jetpack.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack.datamodels.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.awaitResponse


class BookViewModel(appObj: Application) : AndroidViewModel(appObj) {
    private val _bookList =
        MutableStateFlow(Result(state = State.Loading, categoryWithBooks = listOf()))
    val bookList: StateFlow<Result> get() = _bookList

    init {
        fetchBooks()
    }

    private fun fetchBooks() {
        viewModelScope.launch {
            try {
                val call = RestClient().getService().fetchBookList()
                val response = call?.awaitResponse()
                if (response?.isSuccessful == true) {
                    val getResponse = response.body()
                    if (getResponse?.success == 1L) {
                        val list = mutableListOf<CategoryWithBooks>()
                        val categories = mutableMapOf<String, String>()
                        getResponse.booklist.forEach {
                            categories[it.categoryId] = it.categoryName
                        }
                        categories.forEach { map: Map.Entry<String, String> ->
                            val books = getResponse.booklist.filter { it.categoryId == map.key }
                                .toMutableList()
                            books.sortBy { it.title }
                            list.add(
                                CategoryWithBooks(
                                    map.key,
                                    map.value,
                                    books
                                )
                            )
                        }
                        list.sortBy { it.name }
                        _bookList.emit(
                            Result(
                                state = State.Success,
                                categoryWithBooks = list
                            )
                        )
                    } else {
                        _bookList.emit(Result(state = State.Failed, categoryWithBooks = listOf()))
                    }
                } else {
                    _bookList.emit(Result(state = State.Failed, categoryWithBooks = listOf()))
                }
            } catch (e: Exception) {
                Log.e("BookViewModel", e.message ?: "", e)
                _bookList.emit(Result(state = State.Failed, categoryWithBooks = listOf()))
            }
        }
    }
}

data class Result(val state: State, val categoryWithBooks: List<CategoryWithBooks>)

enum class State {
    Success,
    Failed,
    Loading
}