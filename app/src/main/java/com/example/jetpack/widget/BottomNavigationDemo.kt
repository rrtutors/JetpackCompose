package com.example.jetpack.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.jetpack.ui.theme.JetPackTheme
import kotlinx.coroutines.CoroutineScope


@Composable
fun BottomNavigationDemo() {
    val currentScreen = remember { mutableStateOf<DrawerScreens>(DrawerScreens.Home) }

    JetPackTheme(
        darkTheme = true,
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            content = {
                currentScreen.value.screenToLoad()
            },
            bottomBar = {
            }
        )
    }
}