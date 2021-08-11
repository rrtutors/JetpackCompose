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
fun TextDemo() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        Text("Text with color", color = Color.Green)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Text with font size", fontSize = 30.sp)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Text with italic style", fontStyle = FontStyle.Italic)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Text with bold", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Text with cursive font", fontFamily = FontFamily.Cursive)
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            "Text with LineThrough",
            textDecoration = TextDecoration.LineThrough
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            "Text with background color",
            style = TextStyle(background = Color.Yellow)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            "Text with underline",
            textDecoration = TextDecoration.Underline
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Text with overflow ".repeat(50), maxLines = 2, overflow = TextOverflow.Ellipsis)

        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append("Te")
                }
                withStyle(style = SpanStyle(color = Color.Yellow,fontSize = 30.sp)) {
                    append("xt")
                }

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                    append(" Wi")
                }
                withStyle(style = SpanStyle(fontSize = 30.sp)) {
                    append("th ")
                }
                withStyle(style = SpanStyle(background = Color.Gray)) {
                    append("Multiple ")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold,fontSize = 30.sp,textDecoration = TextDecoration.Underline)) {
                    append(" Style")
                }
            }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        SelectionContainer {
            Text("This text is selectable",)
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            "Text with shadow", style = TextStyle(
                color = Color.Yellow,
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif,
                letterSpacing = 4.sp,
                textAlign = TextAlign.Center,
                shadow = Shadow(
                    color = Color.Red,
                    offset = Offset(8f, 8f),
                    blurRadius = 4f
                ),
                textGeometricTransform = TextGeometricTransform(
                    scaleX = 2.5f,
                    skewX = 1f
                )
            )
        )
    }
}