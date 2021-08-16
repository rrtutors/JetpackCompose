package com.example.jetpack.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

import com.example.jetpack.R

@Composable
fun ImageTransparency() {
    val image: Painter = painterResource(id = R.drawable.image_trans)
    Box(content = {
        Image(
            painter = image,
            contentDescription = "image",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxHeight(),
            alpha = 0.2f
        )
        Column(content = {
            Text("Image with transparency",color = Color.Black,fontSize = 30.sp,fontWeight = FontWeight.Bold)  },verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.fillMaxSize())
    })
}