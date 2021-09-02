package com.example.jetpack.widget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SpannableStringDemo() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        Text(text = "Below given text are selectable")
        SelectionContainer(content = {
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Blue,fontSize = 40.sp)) {
                        append("S")
                    }
                    withStyle(style = SpanStyle(color = Color.Red,fontSize = 30.sp)) {
                        append("pa")
                    }

                    withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, color = Color.DarkGray)) {
                        append("nnable")
                    }
                    withStyle(style = SpanStyle(color = Color.Blue,fontSize = 40.sp)) {
                        append(" S")
                    }
                    withStyle(style = SpanStyle(background = Color.Yellow,fontSize = 25.sp)) {
                        append("tring")
                    }
                }
            )

        })
    }
}