package com.example.jetpack.widget

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.jetpack.activities.Activity1
import com.example.jetpack.datamodels.JsonData
import com.example.jetpack.datamodels.JsonDataOps
import com.example.jetpack.datamodels.ParcelableData
import com.example.jetpack.ui.theme.JetPackTheme

@Composable
fun ActivityDemo() {
    val context = LocalContext.current
    JetPackTheme() {
        Column(
            content = {
                Text("Activity 1")
                Text("Name : Rinky")
                Text("Age : 12")
                Text("Gender : Male")
                Text("Param1 : Abhishek")
                Text("Param2 : Shaw")
                Text("Click button below to send data to activity 2")
                Button(onClick = {
                    val intent = Intent(context, Activity1::class.java)
                    intent.putExtra("Name", "Rinky")
                    intent.putExtra("Age", 12)
                    intent.putExtra("IsMale", true)
                    val parcelableData = ParcelableData("Abhishek", "Shaw")
                    intent.putExtra("parcelable_data", parcelableData)
                    val jsonData = JsonData("Abc", "abc@gmail.com")
                    intent.putExtra("json_data", JsonDataOps.toJson(jsonData))
                    context.startActivity(intent)
                }) {
                    Text("Click")
                }
            }, modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
    }
}