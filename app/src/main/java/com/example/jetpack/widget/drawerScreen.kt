package com.example.jetpack.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

val screens = listOf(
    DrawerScreens.Home,
    DrawerScreens.Settings,
    DrawerScreens.ContactUs
)

sealed class DrawerScreens(
    val route: String,
    val title: String,
    val screenToLoad: @Composable () -> Unit,
    val icon: ImageVector
) {
    object Home : DrawerScreens("home", "Home", {
        HomeScreen()
    }, Icons.Default.Home)

    object Settings : DrawerScreens("settings", "Settings", {
        SettingsScreen()
    }, Icons.Default.Settings)

    object ContactUs : DrawerScreens("contactUs", "Contact Us", {
        ContactUsScreen()
    }, Icons.Default.Contacts)
}

@Composable
fun HomeScreen() {
    Column(
        content = {
            Text(text = "You are in Home Screen")
        }, modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
}

@Composable
fun ContactUsScreen() {
    Column(
        content = {
            Text(text = "You are in Contact Us Screen")
        }, modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
}

@Composable
fun SettingsScreen() {
    Column(
        content = {
            Text(text = "You are in Settings Screen")
        }, modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
}