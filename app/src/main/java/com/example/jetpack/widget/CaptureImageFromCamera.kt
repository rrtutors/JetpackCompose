import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp
import com.example.jetpack.ui.theme.JetPackTheme

@Composable
fun CaptureImageFromCamera() {
    JetPackTheme(darkTheme = true) {
        Scaffold(content = {
            val bitmap = remember { mutableStateOf<Bitmap?>(null) }
            val launcher =
                rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
                    bitmap.value = it
                }
            Column(
                modifier = Modifier.padding(16.dp), content = {
                    Button(onClick = {
                        launcher.launch(
                        )
                    }, content = {
                        Text(text = "Capture Image From Camera")
                    })
                    Spacer(modifier = Modifier.padding(16.dp))
                    bitmap.let {
                        val data = it.value
                        if (data != null) {
                            Image(
                                bitmap = data.asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier.size(400.dp)
                            )
                        }
                    }
                })
        })
    }
}