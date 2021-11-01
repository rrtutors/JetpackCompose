package com.example.jetpack.widget

import android.media.MediaPlayer
import android.net.Uri
import android.os.CountDownTimer
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PauseCircleOutline
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material.icons.filled.SettingsVoice
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

fun fetchFormattedDate(date: Date?, format: String): String {
    try {
        if (date != null) {
            val formatter = SimpleDateFormat(format, Locale.getDefault())
            return formatter.format(date)
        }
    } catch (e: Exception) {

    }
    return ""
}


@Composable
fun AudioPlayerDemo() {
    val context = LocalContext.current
    val audioData = remember { mutableStateOf<Uri?>(null) }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
            audioData.value = it
        }
    Scaffold(content = {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp), content = {
            audioData.let {
                val uri = it.value
                if (uri != null) {
                    Row(
                        content = {
                            Card(content = {
                                Icon(
                                    imageVector = Icons.Default.SettingsVoice,
                                    contentDescription = "image",
                                    tint = Color.Red, modifier = Modifier.padding(16.dp)
                                )
                            }, shape = RoundedCornerShape(16.dp))
                            Column(content = {
                                Text(
                                    text = "Audio",
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(start = 16.dp),
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = fetchFormattedDate(
                                        Calendar.getInstance().time,
                                        "dd MMM, yyyy | hh:mm:ss a"
                                    ),
                                    fontSize = 16.sp,
                                    color = Color.LightGray,
                                    modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                                )
                            })
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    )
                    val player = MediaPlayer.create(context, uri)
                    ShowAudioSlider(player = player)
                } else {
                    Button(onClick = {
                        launcher.launch(
                            "audio/*"
                        )
                    }) {
                        Text(text = "Click to Select Audio")
                    }
                }

            }
        })
    }, topBar = {
        TopAppBar(
            backgroundColor = Color.White,
            title = { Text("Audio Player Demo") },
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp), elevation = 10.dp
        )
    })
}

@Composable
fun ShowAudioSlider(player: MediaPlayer?) {
    val playing = remember {
        mutableStateOf(false)
    }
    val position = remember {
        mutableStateOf(0F)
    }
    if (player != null) {
        Slider(
            value = position.value,
            valueRange = 0F..player.duration.toFloat(),
            onValueChange = {
                position.value = it
                player.seekTo(it.toInt())
            }
        )
        Icon(
            imageVector = if (!playing.value || player.currentPosition == player.duration) Icons.Default.PlayCircleOutline else Icons.Default.PauseCircleOutline,
            contentDescription = "image",
            tint = Color.Red, modifier = Modifier
                .padding(16.dp)
                .size(20.dp)
                .clickable(onClick = {
                    if (player.isPlaying) {
                        player.pause()
                        playing.value = false
                    } else {
                        player.start()
                        playing.value = true
                    }

                    object : CountDownTimer(player.duration.toLong(), 100) {

                        override fun onTick(millisUntilFinished: Long) {
                            position.value = player.currentPosition.toFloat()
                            if (player.currentPosition == player.duration) {
                                playing.value = false
                            }
                        }

                        override fun onFinish() {

                        }
                    }.start()
                })
        )
    }

}