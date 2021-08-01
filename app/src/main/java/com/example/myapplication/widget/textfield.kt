package com.example.myapplication.widget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp

//always start composable function using upper case
@Composable
fun TextFieldDemo() {
    val padding = 16.dp
    //Use Column to place items vertically on the screen.
    Column(Modifier.padding(padding)) {
        //The most basic way to display text is to use the Text widget
        Text(
            "Text Field", style = TextStyle(
                fontWeight = FontWeight.Bold
            )
        )
        // we are creating a variable for
        // getting a value of our text field.
        val textState = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            // below line is used to get
            // value of text field,
            value = textState.value,
            // below line is used to get value in text field
            // on value change in text field.
            onValueChange = { textState.value = it },
            // below line is used to add placeholder
            // for our text field.
            placeholder = {
                Text(text = "Enter something")
            },
            // keyboard options is used to modify
            // the keyboard for text field.
            keyboardOptions = KeyboardOptions(
                // keyboard options is used to modify
                // the keyboard for text field.
                keyboardType = KeyboardType.Text,
                // below line is use for capitalization
                // inside our text field.
                capitalization = KeyboardCapitalization.Words,
                // below line is to enable auto
                // correct in our keyboard.
                autoCorrect = false
            ),
            // modifier is use to add padding
            // to our text field.
            modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxWidth(),
            // below line is use to give
            // max lines for our text field.
            maxLines = 4,
            // single line boolean is use to avoid
            // textfield entering in multiple lines.
            singleLine = false,
            // below line is use to specify
            // styling for our text field value.
            textStyle = TextStyle(
                color = Color.Black,

                // below line is used to add font
                // below line is use to change font family.
                fontFamily = FontFamily.SansSerif
            ),

            )
        Text("The textfield has this text: " + textState.value.text)
        Spacer(Modifier.size(padding))
        val textState1 = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            value = textState1.value,
            onValueChange = {
                textState1.value = it
            },
            label = {
                Text(text = "Text Field With Leading Icon")
            },
            // leading icon is use to add icon
            // at the start of text field.
            leadingIcon = {
                // In this method we are specifying
                // our leading icon and its color.
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "image",
                    tint = Color.Blue
                )

            }
        )
        Spacer(Modifier.size(padding))
        val textState2 = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            value = textState2.value,
            onValueChange = { textState2.value = it },
            label = {
                Text(text = "Text Field With Trailing Icon")
            },
            // leading icon is use to add icon
            // at the start of text field.
            trailingIcon = {
                // In this method we are specifying
                // our leading icon and its color.
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "image",
                    tint = Color.Blue
                )
            },
        )
        Spacer(Modifier.size(padding))
        val textState3 = remember { mutableStateOf(TextFieldValue()) }
        OutlinedTextField(
            value = textState3.value,
            onValueChange = {
                textState3.value = it
            },
            label = {
                Text(text = "Outline Text Field")
            },
        )
        Spacer(Modifier.size(padding))
        // we are creating a variable for
        // getting a value of our text field.
        val passwordState = remember { mutableStateOf(TextFieldValue()) }
        val passwordVisibility = remember { mutableStateOf(true) }
        OutlinedTextField(
            value = passwordState.value,
            onValueChange = {
                passwordState.value = it
            },
            label = {
                Text(text = "Password Text Field")
            },
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }) {
                    Icon(
                        imageVector = if (passwordVisibility.value) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = "visibility",
                        tint = Color.Blue
                    )
                }
            },
            visualTransformation = if (passwordVisibility.value) PasswordVisualTransformation() else VisualTransformation.None
        )
        Text("Entered password: " + passwordState.value.text)
    }
}