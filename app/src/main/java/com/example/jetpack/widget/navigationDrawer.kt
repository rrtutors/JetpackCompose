package com.example.jetpack.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack.ui.theme.JetPackTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val currentScreen = remember { mutableStateOf<DrawerScreens>(DrawerScreens.Home) }
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            Drawer(
                selectedScreen = currentScreen.value,
                onMenuSelected = { drawerScreen: DrawerScreens ->
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                    currentScreen.value = drawerScreen
                })
        },
        content = {
            currentScreen.value.screenToLoad()
        },
        topBar = {
            TopAppBarLayout(currentScreen.value, scope, scaffoldState)
        },
    )
}

@Composable
fun TopAppBarLayout(
    currentScreen: DrawerScreens,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    TopAppBar(title = { Text(currentScreen.title) }, navigationIcon = {
        IconButton(onClick = {
            scope.launch {
                scaffoldState.drawerState.open()
            }
        }) {
            Icon(Icons.Filled.Menu, "")
        }
    }, backgroundColor = Color.Blue, contentColor = Color.White)
}

private val screens = listOf(
    DrawerScreens.Home,
    DrawerScreens.Settings,
    DrawerScreens.ContactUs
)

@Composable
fun DrawerHeader() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .background(Color.Blue).padding(16.dp), content = {
        Text("xyz@gmail.com",color = Color.White,fontSize = 20.sp)
    },verticalArrangement = Arrangement.Bottom)
}

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    selectedScreen: DrawerScreens,
    onMenuSelected: ((drawerScreen: DrawerScreens) -> Unit)? = null
) {
    Column(
        modifier
            .fillMaxSize()
    ) {
        DrawerHeader()
        screens.forEach { screen ->

            Row(
                content = {
                    Text(
                        text = screen.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (screen.route == selectedScreen.route) Color.White else Color.Black
                    )
                }, modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .background(
                        color = if (screen.route == selectedScreen.route) Color.Blue else Color.White,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .fillMaxWidth()
                    .clickable(onClick = {
                        onMenuSelected?.invoke(screen)
                    })
                    .padding(vertical = 8.dp, horizontal = 16.dp)

            )
        }
    }
}


sealed class DrawerScreens(
    val route: String,
    val title: String,
    val screenToLoad: @Composable () -> Unit
) {
    object Home : DrawerScreens("home", "Home", {
        HomeScreen()
    })

    object Settings : DrawerScreens("settings", "Settings", {
        SettingsScreen()
    })

    object ContactUs : DrawerScreens("contactUs", "Contact Us", {
        ContactUsScreen()
    })
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