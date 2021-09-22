package com.example.jetpack.widget

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.jetpack.ui.theme.JetPackTheme
import com.example.jetpack.ui.theme.simmerEffectColors
import kotlinx.coroutines.delay

@Composable
fun ShimmerAnimationDemo() {
    JetPackTheme {
        val showLoading = remember { mutableStateOf(true) }
        LazyColumn {
            repeat(5) {
                item {
                    ShimmerAnimation(showLoading.value)
                }
            }
        }
        LaunchedEffect(key1 = null, key2 = null) {
            if(showLoading.value){
                delay(2000L)
                showLoading.value = false
            }
        }
    }
}

@Composable
fun ShimmerAnimation(showLoading: Boolean) {
    val transition = rememberInfiniteTransition()
    val traslateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 2000f,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = 1000,
                easing = FastOutLinearInEasing
            ),
        )
    )

    val brush = Brush.linearGradient(
        colors = simmerEffectColors,
        start = Offset(10f, 10f),
        end = Offset(traslateAnimation.value, traslateAnimation.value)
    )

    ShimmerItem(brush, ShimmerItemData("XYZ", "xyz@gmail.com", "+911234567890"), showLoading)
}

data class ShimmerItemData(val name: String, val email: String, val mobileNo: String)

@Composable
fun ShimmerItem(brush: Brush, item: ShimmerItemData, showLoading: Boolean) {
    ConstraintLayout(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth().border(
                width = 1.2.dp,
                color = Color.LightGray,
                shape = RectangleShape
            )
    ) {
        val (name, email, mobileNo, loader) = createRefs()
        Text(text = item.name, modifier = Modifier
            .padding(horizontal = 16.dp)
            .constrainAs(name) {
                top.linkTo(parent.top, margin = 16.dp)
            })
        Text(text = item.email, modifier = Modifier
            .padding(horizontal = 16.dp)
            .constrainAs(email) {
                top.linkTo(name.bottom, margin = 8.dp)
            })
        Text(text = item.mobileNo, modifier = Modifier
            .padding(16.dp)
            .constrainAs(mobileNo) {
                top.linkTo(email.bottom)
            })
        if (showLoading) {
            Surface(modifier = Modifier
                .constrainAs(loader) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }, content = {
                Spacer(
                    modifier = Modifier
                        .background(brush)
                        .fillMaxSize()
                )
            })
        }

    }
}