package com.example.jetpack.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetpack.ui.theme.JetPackTheme

@ExperimentalFoundationApi
@Composable
fun GridViewDemo() {
    val numbers = (1..30).toList()
    JetPackTheme(
        darkTheme = true,
    ) {
        Scaffold(content = {
            LazyVerticalGrid(
                cells = GridCells.Fixed(2)
            ) {
                items(numbers.size) {
                    Card(content = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp, bottom = 16.dp)
                        ) {
                            Text(text = "  ${it + 1}")
                        }
                    }, modifier = Modifier.padding(start = 16.dp, top = 16.dp))

                }
            }
        }, topBar = {
            TopAppBar(title = { Text("Grid View Demo") }
            )
        })
    }
}
