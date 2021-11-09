package com.example.jetpack.widget

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.jetpack.datamodels.UserInfo
import com.example.jetpack.ui.theme.JetPackTheme
import com.example.jetpack.viewmodels.UserInfoViewModel

@Composable
fun PaginationDemo(userInfoViewModel: UserInfoViewModel) {
    val users: LazyPagingItems<UserInfo> = userInfoViewModel.users.collectAsLazyPagingItems()
    JetPackTheme(darkTheme = true) {
        Scaffold(
            content = {
                LazyColumn {
                    items(users) { item ->
                        UserInfoView(item)
                    }
                }
            },
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("Users") }
                )
            },
        )
    }
}

@Composable
fun UserInfoView(userInfo: UserInfo?) {
    Card(
        elevation = 4.dp,
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp), content = {
                    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
                    val context = LocalContext.current
                    Glide.with(context)
                        .asBitmap()
                        .load(userInfo?.avatar ?: "")
                        .into(object : CustomTarget<Bitmap>() {
                            override fun onLoadCleared(placeholder: Drawable?) {}
                            override fun onResourceReady(
                                resource: Bitmap,
                                transition: Transition<in Bitmap>?
                            ) {
                                bitmap.value = resource
                            }
                        })
                    val value = bitmap.value
                    if (value != null)
                        Image(
                            value.asImageBitmap(),
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp)).width(80.dp).height(80.dp),
                            contentScale = ContentScale.Crop,
                            contentDescription = "image"
                        )
                    else
                        Box(
                            content = {
                                Text(
                                    text = "Loading",
                                    fontSize = 16.sp,
                                )
                            }, modifier = Modifier
                                .width(80.dp)
                                .height(100.dp)
                                .border(
                                    width = 1.2.dp,
                                    color = Color.White,
                                    shape = RectangleShape
                                ),
                            contentAlignment = Alignment.Center
                        )

                    Spacer(modifier = Modifier.size(16.dp))
                    Column(
                        modifier = Modifier.weight(2F),
                        content = {
                            Text(
                                text = "${userInfo?.firstName} ${userInfo?.lastName}",
                                fontSize = 20.sp,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${userInfo?.email}",
                                color = Color.Gray,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        })
                })
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
    )
}
