package com.example.jetpack.widget

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.jetpack.datamodels.Book
import com.example.jetpack.ui.theme.JetPackTheme
import com.example.jetpack.viewmodels.BookViewModel
import com.example.jetpack.viewmodels.State

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntegrateRestApi(bookViewModel: BookViewModel) {
    val books = bookViewModel.bookList.collectAsState()
    JetPackTheme(darkTheme = true) {
        Scaffold(
            content = {
                when (books.value.state) {
                    State.Loading -> {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(
                                progress = 0.8f,
                                color = Color.Magenta,
                                strokeWidth = 4.dp,
                            )
                        }
                    }
                    State.Failed -> {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Failed to load list")
                        }
                    }
                    State.Success -> {
                        LazyColumn(
                            contentPadding = PaddingValues(
                                bottom = 16.dp
                            )
                        ) {
                            books.value.categoryWithBooks.forEach {
                                stickyHeader {
                                    CategoryHeader(it.name)
                                }
                                itemsIndexed(items = it.bookList, itemContent = { pos, book ->
                                    BookListItem(book)
                                })
                            }

                        }
                    }
                }
            },
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("Books") }
                )
            },
        )
    }
}

@Composable
fun CategoryHeader(name: String) {
    Row(
        content = {
            Text(
                text = name.uppercase(),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray)
            .padding(
                top = 4.dp,
                bottom = 4.dp,
                start = 16.dp,
                end = 16.dp
            )
    )
}

@Composable
fun BookListItem(book: Book) {

    Card(
        elevation = 4.dp,
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp), content = {
                    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
                    val context = LocalContext.current
                    Glide.with(context)
                        .asBitmap()
                        .load(book.thumbNail)
                        .into(object : CustomTarget<Bitmap>() {
                            override fun onLoadCleared(placeholder: Drawable?) {}
                            override fun onResourceReady(
                                resource: Bitmap,
                                transition: Transition<in Bitmap>?
                            ) {
                                bitmap.value = resource
                            }
                        })
                    val value = bitmap.value
                    if (value != null)
                        Image(
                            value.asImageBitmap(),
                            contentDescription = "image",
                            Modifier
                                .width(80.dp)
                                .height(100.dp)
                        )
                    else
                        Box(
                            content = {
                                Text(
                                    text = "Loading",
                                    fontSize = 16.sp,
                                )
                            }, modifier = Modifier
                                .width(80.dp)
                                .height(100.dp)
                                .border(
                                    width = 1.2.dp,
                                    color = Color.White,
                                    shape = RectangleShape
                                ),
                            contentAlignment = Alignment.Center
                        )

                    Spacer(modifier = Modifier.size(16.dp))
                    Column(
                        modifier = Modifier.weight(2F),
                        content = {
                            Text(
                                text = book.title,
                                fontSize = 20.sp,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Price: $${book.price}",
                                fontSize = 16.sp,
                                maxLines = 1,
                                color = Color.LightGray,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "Author: ${book.author}",
                                color = Color.Gray,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        })
                })
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
    )
}