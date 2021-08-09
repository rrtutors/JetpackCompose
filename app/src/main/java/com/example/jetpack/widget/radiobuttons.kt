package com.example.jetpack.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RadioButtonDemo() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val selectedGender = remember { mutableStateOf("") }
        Text("Select Gender")
        Spacer(modifier = Modifier.size(16.dp))
        Row {
            RadioButton(selected = selectedGender.value == Gender.male, onClick = {
                selectedGender.value = Gender.male
            })
            Spacer(modifier = Modifier.size(16.dp))
            Text(Gender.male)
            Spacer(modifier = Modifier.size(16.dp))
            RadioButton(selected = selectedGender.value == Gender.female, onClick = {
                selectedGender.value = Gender.female
            },colors = RadioButtonDefaults.colors(Color.Red))
            Spacer(modifier = Modifier.size(16.dp))
            Text(Gender.female)
        }

    }
}

object Gender {
    const val male = "Male"
    const val female = "Female"
}
