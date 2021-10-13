package com.example.jetpack.widget

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.jetpack.ui.theme.JetPackTheme


@Composable
fun PickImageFromGallery() {
    JetPackTheme(darkTheme = true) {
        Scaffold(content = {
            val imageData = remember { mutableStateOf<Uri?>(null) }
            val context = LocalContext.current
            val launcher =
                rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
                    imageData.value = it
                }
            Column(
                modifier = Modifier.padding(16.dp), content = {
                    Button(onClick = {
                        launcher.launch(
                            "image/*"
                        )
                    }, content = {
                        Text(text = "Select Image From Gallery")
                    })
                    imageData.let {
                        val bitmap = remember { mutableStateOf<Bitmap?>(null) }
                        val uri = it.value
                        if (uri != null) {
                            if (Build.VERSION.SDK_INT < 28) {
                                bitmap.value = MediaStore.Images
                                    .Media.getBitmap(context.contentResolver, uri)

                            } else {
                                val source = ImageDecoder
                                    .createSource(context.contentResolver, uri)
                                bitmap.value = ImageDecoder.decodeBitmap(source)
                            }

                            bitmap.value?.let { btm ->
                                Image(
                                    bitmap = btm.asImageBitmap(),
                                    contentDescription = null,
                                    modifier = Modifier.size(400.dp)
                                )
                            }
                        }

                    }
                })
        })
    }
}