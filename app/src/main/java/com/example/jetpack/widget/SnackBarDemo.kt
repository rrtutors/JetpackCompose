package com.example.jetpack.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetpack.ui.theme.JetPackTheme
import kotlinx.coroutines.launch

@Composable
fun SnackBarDemo1() {
    JetPackTheme(
    ) {
        val scaffoldState = rememberScaffoldState()
        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier.fillMaxSize(),
            content = {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "Simple snackbar", modifier = Modifier.padding(8.dp))
                    val showSnackbar = remember { mutableStateOf(false) }

                    Button(onClick = {
                        showSnackbar.value = true
                    }, modifier = Modifier.padding(8.dp)) {
                        Text(text = "Click to show snackbar")
                    }
                    SimpleSnackbar(show = showSnackbar.value, dismiss = {
                        showSnackbar.value = false
                    })
                }

            }, topBar = {
                TopAppBar(
                    title = { Text("Snack Bar Demo") }
                )
            }
        )

    }
}
@Composable
fun SnackBarDemo2() {
    JetPackTheme(
    ) {
        val scaffoldState = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()
        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier.fillMaxSize(),
            content = {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "Snack using snackbarHostState  of scaffoldState",
                        modifier = Modifier.padding(8.dp)
                    )

                    Button(onClick = {
                        coroutineScope.launch {
                            val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                                message = "SnackBar with action Opened Successfully",
                                duration = SnackbarDuration.Indefinite,
                                actionLabel = "Close"
                            )
                            if (snackbarResult == SnackbarResult.ActionPerformed) {
                                scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                            }
                        }
                    }, modifier = Modifier.padding(8.dp)) {
                        Text(text = "Click to show snackbar with action")
                    }

                }

            }, topBar = {
                TopAppBar(
                    title = { Text("Snack Bar Demo") }
                )
            }
        )

    }
}

@Composable
fun SnackBarDemo3() {
    JetPackTheme(
    ) {
        val coroutineScope = rememberCoroutineScope()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            content = {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "Snack using snackbarHostState",
                        modifier = Modifier.padding(8.dp)
                    )
                    val snackbarHostState = remember {
                        SnackbarHostState()
                    }
                    Button(onClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "SnackBar Opened Successfully",
                                duration = SnackbarDuration.Indefinite,
                                actionLabel = "Okay"
                            )
                        }
                    }, modifier = Modifier.padding(8.dp)) {
                        Text(text = "Click to show snackbar with action")
                    }
                    SnackbarWithSnackbarHostState(snackbarHostState)

                }

            }, topBar = {
                TopAppBar(
                    title = { Text("Snack Bar Demo") }
                )
            }
        )

    }
}

@Composable
fun SnackbarWithSnackbarHostState(snackbarHostState: SnackbarHostState) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (snackbar) = createRefs()
        SnackbarHost(modifier = Modifier
            .padding(horizontal = 16.dp)
            .constrainAs(snackbar) {
                bottom.linkTo(parent.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
            }, hostState = snackbarHostState, snackbar = {
            Snackbar(action = {
                Text(
                    text = "${snackbarHostState.currentSnackbarData?.actionLabel}",
                    modifier = Modifier.clickable(onClick = {
                        snackbarHostState.currentSnackbarData?.dismiss()
                    })
                )
            }) {
                Text(
                    "${
                        snackbarHostState.currentSnackbarData?.message
                    }"
                )
            }
        })
    }
}

@Composable
fun SimpleSnackbar(show: Boolean, dismiss: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (snackbar) = createRefs()
        if (show) {
            Snackbar(content = {
                Text(text = "Snackbar")
            }, modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(snackbar) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                }, action = {
                Text(text = "Dismiss", modifier = Modifier.clickable(onClick = {
                    dismiss()
                }))
            })
        }
    }
}