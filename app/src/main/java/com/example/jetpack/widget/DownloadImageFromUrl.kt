package com.example.jetpack.widget

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.jetpack.MainActivity
import com.example.jetpack.workers.DownloadFileWorker

const val url =
    "http://digitalcommunications.wp.st-andrews.ac.uk/files/2019/04/JPEG_compression_Example.jpg"

@Composable
fun DownloadImageFromUrl() {
    val context = LocalContext.current
    val selectedFile = remember { mutableStateOf<ActivityResult?>(null) }
    val showImage = remember { mutableStateOf<Uri?>(null) }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            selectedFile.value = it
        }
    if (selectedFile.value != null && selectedFile.value?.resultCode == Activity.RESULT_OK) {
        val uri = selectedFile.value?.data?.data
        if (uri != null) {
            val builder = Data.Builder()
            builder.putString("destination", uri.toString())
            builder.putString("url", url)
            val inputParams = builder.build()
            val downloadFileWorker = OneTimeWorkRequest.Builder(DownloadFileWorker::class.java)
                .setInputData(inputParams)
                .build()
            val workManager = WorkManager.getInstance(context)
            workManager.enqueue(downloadFileWorker)
            val outputWorkInfo: LiveData<WorkInfo> =
                workManager.getWorkInfoByIdLiveData(downloadFileWorker.id)
            outputWorkInfo.observe(context as MainActivity, Observer {
                if (it.state == WorkInfo.State.SUCCEEDED) {
                    showImage.value=uri
                } else if (it.state == WorkInfo.State.FAILED) {

                }
            })

        }
    }
    val mimeType = fetchMimeTypeFromUrl()
    val fileName = "flower_image.jpg"
    Column(
        content = {
            Text(fileName)
            TextButton(onClick = {
                if (fileName.isNotEmpty() && mimeType?.isNotEmpty() == true) {
                    val intent = Intent()
                    intent.action = Intent.ACTION_CREATE_DOCUMENT
                    val mimeTypes: Array<String> = arrayOf(mimeType)
                    intent.type = mimeType
                    intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
                    intent.putExtra(Intent.EXTRA_TITLE, fileName)
                    launcher.launch(
                        Intent.createChooser(
                            intent,
                            "Select a target to download the file"
                        )
                    )
                }
            }, content = {
                Text(text = "Download")
            })
            if (showImage.value != null) {
                val bitmap = remember { mutableStateOf<Bitmap?>(null) }
                Glide.with(context)
                    .asBitmap()
                    .load(showImage.value)
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onLoadCleared(placeholder: Drawable?) {}
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            bitmap.value = resource
                        }
                    })
                val value = bitmap.value
                if (value != null){
                    Image(value.asImageBitmap(), contentDescription = "image", Modifier.fillMaxWidth())
                }else{
                    Text("Downlaoding Image...")
                }
            }

        }, modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp)
    )
}

private fun fetchMimeTypeFromUrl(): String? {
    var type: String? = null
    try {
        val extension = MimeTypeMap.getFileExtensionFromUrl(url)
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }
    } catch (e: Exception) {

    }
    return type
}