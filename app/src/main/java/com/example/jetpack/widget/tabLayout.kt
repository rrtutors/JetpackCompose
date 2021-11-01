package com.example.jetpack.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jetpack.ui.theme.JetPackTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


private val tabs = listOf(
    TabItem.Home,
    TabItem.Settings,
    TabItem.Contacts
)

@ExperimentalPagerApi
@Composable
fun TabLayoutDemo() {
    JetPackTheme(
        darkTheme = true,
    ) {
        val pagerState = rememberPagerState()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            content = {
                TabPage(tabItems = tabs, pagerState = pagerState)
            },
            topBar = {
                val coroutineScope = rememberCoroutineScope()
                Column(content = {
                    TopAppBar(title = { Text("Tab Layout Demo") }
                    )
                    //Replace here with TextTabLayout or ScrollableTabLayout or IconTabLayout
                    IconWithTextTabLayout(
                        tabs,
                        selectedIndex = pagerState.currentPage,
                        onPageSelected = { tabItem: TabItem ->
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(tabItem.index)
                            }
                        })
                })
            },
        )
    }
}

@ExperimentalPagerApi
@Composable
fun IconWithTextTabLayout(
    tabs: List<TabItem>,
    selectedIndex: Int,
    onPageSelected: ((tabItem: TabItem) -> Unit)
) {
    TabRow(selectedTabIndex = selectedIndex) {
        tabs.forEachIndexed { index, tabItem ->
            Tab(selected = index == selectedIndex, onClick = {
                onPageSelected(tabItem)
            }, text = {
                Text(text = tabItem.title)
            }, icon = {
                Icon(tabItem.icon, "")
            })
        }
    }
}

@Composable
fun ScrollableTabLayout(
    tabs: List<TabItem>,
    selectedIndex: Int,
    onPageSelected: ((tabItem: TabItem) -> Unit)
) {
    ScrollableTabRow(selectedTabIndex = selectedIndex) {
        tabs.forEachIndexed { index, tabItem ->
            Tab(selected = index == selectedIndex, onClick = {
                onPageSelected(tabItem)
            }, text = {
                Text(text = tabItem.title)
            }, icon = {
                Icon(tabItem.icon, "")
            })
        }
    }
}

@Composable
fun TextTabLayout(
    tabs: List<TabItem>,
    selectedIndex: Int,
    onPageSelected: ((tabItem: TabItem) -> Unit)
) {
    TabRow(selectedTabIndex = selectedIndex) {
        tabs.forEachIndexed { index, tabItem ->
            Tab(selected = index == selectedIndex, onClick = {
                onPageSelected(tabItem)
            }, text = {
                Text(text = tabItem.title)
            })
        }
    }
}

@Composable
fun IconTabLayout(
    tabs: List<TabItem>,
    selectedIndex: Int,
    onPageSelected: ((tabItem: TabItem) -> Unit)
) {
    TabRow(selectedTabIndex = selectedIndex) {
        tabs.forEachIndexed { index, tabItem ->
            Tab(selected = index == selectedIndex, onClick = {
                onPageSelected(tabItem)
            }, icon = {
                Icon(tabItem.icon, "")
            })
        }
    }
}


@ExperimentalPagerApi
@Composable
fun TabPage(pagerState: PagerState, tabItems: List<TabItem>) {
    HorizontalPager(
        count = tabs.size,
        state = pagerState
    ) { index ->
        tabItems[index].screenToLoad()
    }
}

sealed class TabItem(
    val index:Int,
    val icon: ImageVector,
    val title: String,
    val screenToLoad: @Composable () -> Unit
) {
    object Home : TabItem(0, Icons.Default.Home, "Home", {
        HomeScreenForTab()
    })
    object Contacts : TabItem(2, Icons.Default.Contacts, "Contacts", {
        ContactScreenForTab()
    })
    object Settings : TabItem(1, Icons.Default.Settings, "Settings", {
        SettingsScreenForTab()
    })
}

@Composable
fun HomeScreenForTab() {
    Column(
        content = {
            Text(text = "You are in Home Screen")
        }, modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
}

@Composable
fun ContactScreenForTab() {
    Column(
        content = {
            Text(text = "You are in Contacts Screen")
        }, modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
}

@Composable
fun SettingsScreenForTab() {
    Column(
        content = {
            Text(text = "You are in Settings Screen")
        }, modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
}