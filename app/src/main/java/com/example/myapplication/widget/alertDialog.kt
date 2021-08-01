package com.example.myapplication.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun AlertDialogDemo() {
    val openDialog = remember { mutableStateOf(true) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { openDialog.value = true }) {
            Text(text = "Open Alert dialog")
        }
        if (openDialog.value) {
            // below line is use to
            // display a alert dialog.
            AlertDialog(
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onCloseRequest.
                onDismissRequest = {
                    openDialog.value = false
                },
                // below line is use to display title of our dialog
                // box and we are setting text color to white.
                title = {
                    Text(text = "Alert Dialog", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                },
                // below line is use to display
                // description to our alert dialog.
                text = {
                    Text("Are you sure you want to close this dialog?", fontSize = 16.sp)
                },
                // in below line we are displaying
                // our confirm button.
                confirmButton = {
                    TextButton(
                        onClick = {
                            openDialog.value = false
                        }) {
                        Text("Yes",style = TextStyle(color = Color.White))
                    }
                },
                // in below line we are displaying
                // our dismiss button.
                dismissButton = {
                    TextButton(
                        onClick = {
                            openDialog.value = false
                        }) {
                        Text("No",style = TextStyle(color = Color.White))
                    }
                },
                // below line is use to add background color to our alert dialog
                backgroundColor = Color.Blue,
                // below line is use to add content color for our alert dialog.
                contentColor = Color.White
            )
        }
    }

}