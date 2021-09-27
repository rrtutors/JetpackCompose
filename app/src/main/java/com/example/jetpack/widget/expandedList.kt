package com.example.jetpack.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jetpack.datamodels.ExpandedListItem
import com.example.jetpack.datamodels.User
import com.example.jetpack.ui.theme.JetPackTheme
import com.example.jetpack.viewmodels.UserViewModel


@Composable
fun ExpandableListDemo(userViewModel: UserViewModel) {
    val users = userViewModel.users.collectAsState()

    JetPackTheme() {
        LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp)) {
            itemsIndexed(items = users.value, itemContent = { pos, item ->
                Column(content = {
                    ExpandedListItemView(
                        item,
                        onButtonClicked = { id: String, expanded: Boolean, index: Int ->
                            userViewModel.changeItemValue(
                                index,
                                expanded = expanded
                            )
                        })
                    if (item.expanded) {
                        UserListItemView(user = item.data)
                    }
                })
            })

        }
    }
}

@Composable
fun ExpandedListItemView(
    item: ExpandedListItem,
    onButtonClicked: ((id: String, expanded: Boolean, index: Int) -> Unit)? = null
) {
    Row(
        content = {
            Text(item.data.name, modifier = Modifier.weight(1F), fontWeight = FontWeight.Bold)
            Icon(
                imageVector = if (item.expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                contentDescription = "image",
                tint = Color.Black, modifier = Modifier
                    .size(30.dp)
                    .clickable(onClick = {
                        onButtonClicked?.invoke(item.data.id, !item.expanded, item.index)
                    })
            )
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    )
}

@Composable
fun UserListItemView(user: User) {
    Column(content = {
        Text(text = user.emailId, color = Color.Gray)
    })
}
