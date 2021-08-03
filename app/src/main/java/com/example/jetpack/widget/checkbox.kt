package com.example.jetpack.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CheckBoxDemo() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unchecked Check Box")
        Spacer(modifier = Modifier.size(16.dp))
        Row {
            val isChecked = remember { mutableStateOf(false) }
            Checkbox(
                checked = isChecked.value,
                onCheckedChange = {
                    isChecked.value = it
                },
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text("A")

        }
        Spacer(modifier = Modifier.size(16.dp))
        Text("Checked Check Box")
        Spacer(modifier = Modifier.size(16.dp))
        Row {
            val isChecked = remember { mutableStateOf(true) }
            Checkbox(
                checked = isChecked.value,
                onCheckedChange = {
                    isChecked.value = it
                },
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text("B")

        }
        Spacer(modifier = Modifier.size(16.dp))
        Text("Color Check Box")
        Spacer(modifier = Modifier.size(16.dp))
        Row {
            val isChecked = remember { mutableStateOf(true) }
            Checkbox(
                checked = isChecked.value,
                onCheckedChange = {
                    isChecked.value = it
                },
                colors = CheckboxDefaults.colors(Color.Red)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text("Color")

        }
        Spacer(modifier = Modifier.size(16.dp))
        Text("Grouped Check Box")
        val fruitsList = arrayListOf<String>("Apple", "Mango", "Orange")
        fruitsList.forEach { option: String ->
            Spacer(modifier = Modifier.size(16.dp))
            Row {
                val isChecked = remember { mutableStateOf(false) }
                Checkbox(
                    checked = isChecked.value,
                    onCheckedChange = {
                        isChecked.value = it
                    }
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(option)
            }
        }
    }
}