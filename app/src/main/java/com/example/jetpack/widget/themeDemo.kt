package com.example.jetpack.widget

import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack.ui.theme.JetPackTheme

data class ThemeData(
    val title: String,
    val value: Boolean
)

object ThemeMode {
    const val Dark = "Dark"
    const val Light = "Light"
    const val System = "System"
}

@Composable
fun ThemeDemo(
) {
    val mode = remember { mutableStateOf(ThemeData(ThemeMode.Light, false)) }
    JetPackTheme(
        darkTheme = mode.value.value,
        content = {
            val value = isSystemInDarkTheme()
            Scaffold(content = {
                Column(content = {
                    Row {
                        RadioButton(
                            selected = mode.value.title == ThemeMode.Dark,
                            onClick = {
                                mode.value = ThemeData(ThemeMode.Dark, true)
                            })
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(
                            ThemeMode.Dark,
                            color = MaterialTheme.colors.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        RadioButton(
                            selected = mode.value.title == ThemeMode.Light,
                            onClick = {
                                mode.value = ThemeData(ThemeMode.Light, false)
                            })
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(
                            ThemeMode.Light,
                            color = MaterialTheme.colors.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        RadioButton(
                            selected = mode.value.title == ThemeMode.System,
                            onClick = {
                                mode.value = ThemeData(ThemeMode.System, value)
                            })
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(
                            ThemeMode.System,
                            color = MaterialTheme.colors.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Card(
                        content = {
                            Column(content = {
                                Text("Card with dark and light theme")
                            }, modifier = Modifier.padding(16.dp))
                        }, modifier = Modifier.fillMaxWidth()
                    )
                    val name = "Abhishek"
                    val gender = "Male"
                    val emailId = "Abcded@gmail.com"
                    Spacer(modifier = Modifier.size(16.dp))
                    Card(modifier = Modifier
                        .fillMaxWidth(), content = {
                        Row(
                            Modifier
                            .padding(16.dp),
                            content = {
                                val color =
                                    Color.Red
                                Box(
                                    content = {
                                        Text(
                                            text = name[0].uppercase(),
                                            fontSize = 24.sp
                                        )
                                    }, modifier = Modifier
                                        .size(80.dp)
                                        .border(
                                            width = 1.2.dp,
                                            color = color,
                                            shape = CircleShape
                                        ),
                                    contentAlignment = Alignment.Center
                                )
                                Spacer(modifier = Modifier.size(16.dp))
                                Column(
                                    modifier = Modifier.weight(2F),
                                    content = {
                                        Spacer(modifier = Modifier.size(8.dp))
                                        Text(
                                            text = name.uppercase(),
                                            fontSize = 16.sp,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        Text(
                                            text = gender,
                                            fontSize = 14.6.sp
                                        )
                                        Text(
                                            text = emailId,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    })

                            })
                    })

                }, modifier = Modifier.padding(16.dp))
            }, topBar = {
                TopAppBar(
                    title = { Text("Theme Demo") }
                )
            })
        }
    )
}
