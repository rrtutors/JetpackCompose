package com.example.jetpack.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable()
fun CardDemo() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            content = {
                Text("Card with content argument", modifier = Modifier.padding(16.dp))
            }
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            //set shape of the card
            shape = RoundedCornerShape(16.dp),
            content = {
                Text("Card with shape argument", modifier = Modifier.padding(16.dp))
            }
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            //set background color of the card
            backgroundColor = Color.Gray,
            content = {
                Text("Card with background color argumentt", modifier = Modifier.padding(16.dp))
            }
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            //set elevation of the card
            elevation = 10.dp,
            content = {
                Text("Card with elevation argument", modifier = Modifier.padding(16.dp))
            }
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            border = BorderStroke(2.dp, Color.Black),
            content = {
                Text("Card with border argument", modifier = Modifier.padding(16.dp))
            }
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentColor = Color.Green,
            content = {
                Text("Card with content color argument", modifier = Modifier.padding(16.dp))
            }
        )

    }
}
