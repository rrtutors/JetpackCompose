package com.example.jetpack.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch


data class BottomSheetItem(val title: String, val icon: ImageVector)

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun BottomSheetDemo() {
    val bottomSheetItems = listOf(
        BottomSheetItem(title = "Folder", icon = Icons.Default.Folder),
        BottomSheetItem(title = "Upload", icon = Icons.Default.Upload),
        BottomSheetItem(title = "Scan", icon = Icons.Default.Scanner),
        BottomSheetItem(title = "Google Docs", icon = Icons.Default.Receipt),
        BottomSheetItem(title = "Google Sheets", icon = Icons.Default.Receipt),
        BottomSheetItem(title = "Settings", icon = Icons.Default.Settings)
    )
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Column(
                content = {
                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(
                        text = "Create New",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 21.sp,
                        color = Color.White
                    )
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(3)
                    ) {
                        items(bottomSheetItems.size, itemContent = {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 24.dp)
                            ) {
                                Spacer(modifier = Modifier.padding(8.dp))
                                Icon(
                                    bottomSheetItems[it].icon,
                                    "",
                                    tint = Color.White
                                )
                                Spacer(modifier = Modifier.padding(8.dp))
                                Text(text = bottomSheetItems[it].title, color = Color.White)
                            }

                        })
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color(0xAA3fa7cc))
                    .padding(16.dp)
            )
        },
        sheetPeekHeight = 0.dp,
        topBar = {
            TopAppBar(
                title = { Text("Bottom Sheet Demo") },
                backgroundColor = Color.White,
                contentColor = Color.Blue
            )
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Button(
                modifier = Modifier
                    .padding(20.dp),
                onClick = {
                    coroutineScope.launch {
                        if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        } else {
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                        }
                    }
                }
            ) {
                Text(
                    text = "Click to show Bottom Sheet"
                )
            }
        }
    }
}