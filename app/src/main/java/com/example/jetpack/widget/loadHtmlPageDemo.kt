package com.example.jetpack.widget

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun LoadHtml() {
    val loadHtmlPage = remember { mutableStateOf<Boolean>(false) }
    val context = LocalContext.current
    val htmlPage = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<body>\n" +
            "\n" +
            "<h2>Fruits List</h2>\n" +
            "\n" +
            "<ul>\n" +
            "  <li>Apple</li>\n" +
            "  <li>Mango</li>\n" +
            "  <li>Banana</li>\n" +
            "</ul>  \n" +
            "\n" +
            "<h2>Language</h2>\n" +
            "\n" +
            "<ol>\n" +
            "  <li>Hindi</li>\n" +
            "  <li>English</li>\n" +
            "  <li>Bengali</li>\n" +
            "  <li>Spanish</li>\n" +
            "</ol> \n" +
            "\n" +
            "</body>\n" +
            "</html>"
    Column(
        content = {
            if (!loadHtmlPage.value) {
                Button(onClick = {
                    loadHtmlPage.value = true
                }) {
                    Text("Load Html Page")
                }
            }

            if (loadHtmlPage.value) {
                AndroidView(factory = {
                    WebView(context).apply {
                        webViewClient = WebViewClient()
                        loadDataWithBaseURL(null, htmlPage, "text/HTML", "UTF-8", null)
                    }
                })
            }

        }, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    )
}