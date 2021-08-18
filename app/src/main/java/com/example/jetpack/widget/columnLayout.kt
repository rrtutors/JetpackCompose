package com.example.jetpack.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColumnLayoutDemo() {
    Row(modifier = Modifier.fillMaxSize()) {

        Column(
            content = {
                Box(
                    modifier = Modifier
                        .background(color = Color.Green)
                        .height(100.dp)
                        .width(50.dp)
                )
                Box(
                    modifier = Modifier
                        .background(color = Color.Black)
                        .height(100.dp)
                        .width(50.dp)
                )
                Box(
                    modifier = Modifier
                        .background(color = Color.Blue)
                        .height(100.dp)
                        .width(50.dp)
                )
            },
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.White)
        )



        Column(
            content = {
                Box(
                    modifier = Modifier
                        .background(color = Color.Green)
                        .height(100.dp)
                        .width(50.dp)
                )
                Box(
                    modifier = Modifier
                        .background(color = Color.Black)
                        .height(100.dp)
                        .width(50.dp)
                )
                Box(
                    modifier = Modifier
                        .background(color = Color.Blue)
                        .height(100.dp)
                        .width(50.dp)
                )
            },
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.Gray)
        )

        Column(
            content = {
                Box(
                    modifier = Modifier
                        .background(color = Color.Green)
                        .height(100.dp)
                        .width(50.dp)
                )
                Box(
                    modifier = Modifier
                        .background(color = Color.Black)
                        .height(100.dp)
                        .width(50.dp)
                )
                Box(
                    modifier = Modifier
                        .background(color = Color.Blue)
                        .height(100.dp)
                        .width(50.dp)
                )
            },
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
        )
        Column(
            content = {
                Box(
                    modifier = Modifier
                        .background(color = Color.Green)
                        .height(100.dp)
                        .width(50.dp)
                )
                Box(
                    modifier = Modifier
                        .background(color = Color.Black)
                        .height(100.dp)
                        .width(50.dp)
                )
                Box(
                    modifier = Modifier
                        .background(color = Color.Blue)
                        .height(100.dp)
                        .width(50.dp)
                )
            },
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.Gray)
        )
        Column(
            content = {
                Box(
                    modifier = Modifier
                        .background(color = Color.Green)
                        .height(100.dp)
                        .width(50.dp)
                )
                Box(
                    modifier = Modifier
                        .background(color = Color.Black)
                        .height(100.dp)
                        .width(50.dp)
                )
                Box(
                    modifier = Modifier
                        .background(color = Color.Blue)
                        .height(100.dp)
                        .width(50.dp)
                )
            },
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxHeight()
        )
        Column(
            content = {
                Box(
                    modifier = Modifier
                        .background(color = Color.Green)
                        .height(100.dp)
                        .width(50.dp)
                )
                Box(
                    modifier = Modifier
                        .background(color = Color.Black)
                        .height(100.dp)
                        .width(50.dp)
                )
                Box(
                    modifier = Modifier
                        .background(color = Color.Blue)
                        .height(100.dp)
                        .width(50.dp)
                )
            },
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxHeight()
        )

    }
}