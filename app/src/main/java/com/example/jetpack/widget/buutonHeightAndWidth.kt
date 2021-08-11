package com.example.jetpack.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonDemo(){
    Column(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(16.dp)){
        Text(text = "Button with height and width")
        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick = {},content={
            Text(text = "Button 1")
        })
        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick = {},modifier = Modifier.size(width = 200.dp,height = 60.dp),content={
            Text(text = "Button 2")
        })
        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick = {},modifier = Modifier.size(width = 150.dp,height = 35.dp),content={
            Text(text = "Button 3")
        })
        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick = {},modifier = Modifier.fillMaxWidth(),content={
            Text(text = "Button 4")
        })
    }
}