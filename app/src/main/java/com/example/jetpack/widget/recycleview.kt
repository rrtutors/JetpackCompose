package com.example.jetpack.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack.datamodels.User

@Composable
fun LazyColumnDemo() {

    val users = listOf(
        User(id = "1", name = "Ankit Singh", emailId = "abc@gmail.com"),
        User(id = "2", name = "Rishabh Shaw", emailId = "xyz@gmail.com"),
        User(id = "3", name = "Neha Shaw", emailId = "abc@gmail.com"),
        User(id = "4", name = "Ekta Gupta", emailId = "xyz@gmail.com"),
        User(id = "5", name = "Rahul Jaiswal", emailId = "abc@gmail.com"),
        User(id = "6", name = "Anindita Chatterjee", emailId = "xyz@gmail.com"),
        User(id = "7", name = "Aakash Raj", emailId = "abc@gmail.com"),
        User(id = "8", name = "Arpita Ghosh", emailId = "xyz@gmail.com"),
        User(id = "9", name = "Arvind Patel", emailId = "abc@gmail.com"),
        User(id = "10", name = "Akash Tiwari", emailId = "xyz@gmail.com")
    )
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp)) {
        items(
            items = users,
            itemContent = {
                UserListItem(user = it)
            })
    }
}

@Composable
fun UserListItem(user: User) {
    Card(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = user.name,
                style = TextStyle(
                    color = Color.Blue,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(text = user.emailId, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

//https://betterprogramming.pub/goodbye-recyclerview-adapters-hello-lists-in-jetpack-compose-d0a389a1683e
