package com.example.jetpack.widget

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack.datamodels.Customer
import com.example.jetpack.ui.theme.JetPackTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            SwipeRefreshLayout()
        }
    }
}

@Composable
fun SwipeRefreshLayout() {
    val customerList = listOf(
        Customer(),
        Customer(),
        Customer(),
        Customer(),
        Customer(),
        Customer(),
        Customer(),
        Customer(),
        Customer(),
        Customer(),
        Customer()
    )
    var isRefreshing by remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)
    JetPackTheme(
        darkTheme = true,
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            content = {
                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = {
                        isRefreshing = true
                    },
                ) {
                    LazyColumn(content = {
                        items(
                            items = customerList,
                            itemContent = {

                                CustomerListItemView(it)
                            })
                    })
                }

            },
            topBar = {
                TopAppBar(title = { Text("Swipe To Refresh") }
                )
            },
        )
        LaunchedEffect(isRefreshing) {
            if (isRefreshing) {
                delay(1000L)
                isRefreshing= false
            }
        }
    }
}

@Composable
fun CustomerListItemView(it: Customer) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp), content = {
            val color =
                Color(
                    Random.nextInt(256),
                    Random.nextInt(256),
                    Random.nextInt(256)
                )
            Box(
                content = {
                    Text(
                        text = it.name.get(0).uppercase(),
                        fontSize = 24.sp,
                        color = color
                    )
                }, modifier = Modifier
                    .size(80.dp)
                    .border(width = 1.2.dp, color = color, shape = CircleShape),
                contentAlignment = Alignment.Center
            )
            Spacer(modifier = Modifier.size(16.dp))
            Column(
                modifier = Modifier.weight(2F),
                content = {
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = it.name.uppercase(),
                        color = color,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "${it.gender}",
                        color = Color.White,
                        fontSize = 14.6.sp
                    )
                    Text(
                        text = "${it.emailId}",
                        color = Color.Gray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                })
        })
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SwipeRefreshLayout()
}

