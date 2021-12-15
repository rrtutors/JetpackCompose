package com.example.jetpack.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack.ui.theme.JetPackTheme
import com.example.jetpack.viewmodels.SearchViewModel

@Composable
fun SearchViewDemo(searchViewModel: SearchViewModel) {

    JetPackTheme(
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            content = {
                SearchContent(searchViewModel)

            }, topBar = {
                TopAppBar(
                    title = { Text("Search View Demo") }
                )
            }
        )
    }
}

@Composable
fun SearchContent(searchViewModel: SearchViewModel) {
    val searchList = searchViewModel.searchList.collectAsState()
    val searchBy = remember { mutableStateOf(TextFieldValue("")) }
    Column(
        Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxSize()
    ) {
        SearchViewTextField(searchBy)
        searchViewModel.searchedItems(searchBy.value.text)
        LazyColumn(
            contentPadding = PaddingValues(
                bottom = 100.dp
            )
        ) {
            items(
                items = searchList.value,
                itemContent = {
                    SearchListItem(searchData = it)
                })
        }
    }
}

@Composable
fun SearchListItem(searchData: SearchData) {
    Card(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        border = BorderStroke(width = 1.2.dp, Color.Blue)
    ) {
        Row(content = {
            Icon(
                Icons.Default.Contacts,
                "",
                tint = Color.Blue,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "${searchData.name}",
                    style = TextStyle(
                        color = Color.Blue,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(text = "${searchData.emailId}", modifier = Modifier.padding(top = 8.dp))
            }
        })

    }
}

@Composable
fun SearchViewTextField(state: MutableState<TextFieldValue>) {
    Box(
        modifier = Modifier
            .border(width = 1.dp, color = Color.Gray, shape = CircleShape)
            .fillMaxWidth()
    ) {
        BasicTextField(
            value = state.value,
            onValueChange = {
                state.value = it
            },
            modifier = Modifier
                .background(Color.White, CircleShape)
                .height(38.dp)
                .fillMaxWidth(),
            singleLine = true,
            maxLines = 1,
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "image",
                        tint = Color.Blue
                    )
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (state.value == TextFieldValue("")) Text(
                            "Search"
                        )
                        innerTextField()
                    }
                    if (state.value != TextFieldValue("")) {
                        IconButton(
                            onClick = {
                                state.value = TextFieldValue("")
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "image",
                                tint = Color.Blue
                            )
                        }
                    }
                }
            }
        )
    }
}

data class SearchData(var name: String? = null, var emailId: String? = null)