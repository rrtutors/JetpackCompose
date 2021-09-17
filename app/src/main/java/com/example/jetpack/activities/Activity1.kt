package com.example.jetpack.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack.activities.ui.theme.MyApplicationTheme
import com.example.jetpack.datamodels.JsonDataOps
import com.example.jetpack.datamodels.ParcelableData

class Activity1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ShowData()
                }
            }
        }
    }
}

@Composable
fun ShowData() {
    val context = LocalContext.current
    val intent = (context as Activity1).intent
    val name = intent.getStringExtra("Name")
    val age = intent.getIntExtra("Age", 0)
    val isMale = intent.getBooleanExtra("IsMale", false)
    val parcelableData = intent.getParcelableExtra<ParcelableData>("parcelable_data")
    val jsonData = JsonDataOps.fromStringToData(intent.getStringExtra("json_data"))
    Column(
        content = {
            Text("Activity 2")
            Text("Name : $name")
            Text("Age : $age")
            Text("Gender : ${if (isMale) "Male" else "Female"}")
            Text("Parcelable Data")
            Text("Param1 : ${parcelableData?.param1}")
            Text("Param2 : ${parcelableData?.param2}")
            Text("Json Data")
            Text("Param1 : ${jsonData?.param1}")
            Text("Param2 : ${jsonData?.param2}")
        }, modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MyApplicationTheme {
        ShowData()
    }
}