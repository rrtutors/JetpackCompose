package com.example.jetpack.widget

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun FloatingActionMenu() {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FloatingActionButton(onClick = {
            Toast.makeText(
                context,
                "You clicked floating action button with circular shape....",
                Toast.LENGTH_LONG
            ).show()
        }, content = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "image",
                tint = Color.White
            )
        }, backgroundColor = Color.Blue)
        Spacer(Modifier.size(16.dp))
        FloatingActionButton(onClick = {
            Toast.makeText(
                context,
                "You clicked floating action button with rectangle shape....",
                Toast.LENGTH_LONG
            ).show()
        }, content = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "image",
                tint = Color.White
            )
        }, backgroundColor = Color.Blue, shape = RectangleShape)
        Spacer(Modifier.size(16.dp))
        ExtendedFloatingActionButton(
            backgroundColor = Color.Blue,
            text = {
                Text(text = "Extended FAB", color = Color.White)
            },
            onClick = {
                Toast.makeText(
                    context,
                    "You clicked extended floating action button ....",
                    Toast.LENGTH_LONG
                ).show()
            })
        Spacer(Modifier.size(16.dp))
        ExtendedFloatingActionButton(
            backgroundColor = Color.Blue,
            text = {
                Text(text = "Extended FAB", color = Color.White)
            },
            onClick = {
                Toast.makeText(
                    context,
                    "You clicked extended floating action button with icon....",
                    Toast.LENGTH_LONG
                ).show()
            }, icon = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "image",
                    tint = Color.White
                )
            })

        Spacer(Modifier.size(16.dp))
        ExtendedFloatingActionButton(
            backgroundColor = Color.Blue,
            text = {
                Text(text = "Extended FAB", color = Color.White)
            },
            onClick = {
                Toast.makeText(
                    context,
                    "You clicked extended floating action button with rectangle shape....",
                    Toast.LENGTH_LONG
                ).show()
            }, shape = RectangleShape
        )
    }
}