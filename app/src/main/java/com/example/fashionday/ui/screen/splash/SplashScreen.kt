package com.example.fashionday.ui.screen.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fashionday.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }

    val charactersScale =
        remember { Animatable(0.5f) }
    val charactersAlpha = remember { Animatable(0f) }

    val textAlpha = remember { Animatable(0f) }
    val textTranslationY = remember { Animatable(50f) }

    LaunchedEffect(key1 = "animation") {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        )
        offsetY.animateTo(
            targetValue = -200f,
            animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        )

        charactersScale.animateTo(
            targetValue = 1.5f,
            animationSpec = tween(durationMillis = 1500, easing = LinearOutSlowInEasing)
        )
        charactersAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1500, easing = LinearOutSlowInEasing)
        )

        textAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, delayMillis = 1700)
        )
        textTranslationY.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 1000, delayMillis = 1700)
        )

        delay(3000)
        navController.navigate("main") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_big),
            contentDescription = "Logo",
            modifier = Modifier
                .graphicsLayer(
                    scaleX = scale.value,
                    scaleY = scale.value,
                    translationY = offsetY.value - 150.dp.value
                )
                .size(400.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.rick_and_morty),
            contentDescription = "Characters",
            modifier = Modifier
                .graphicsLayer(
                    scaleX = charactersScale.value,
                    scaleY = charactersScale.value,
                    alpha = charactersAlpha.value,
                    translationY = 150.dp.value
                )
                .align(Alignment.Center)
        )

        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
                .graphicsLayer {
                    alpha = textAlpha.value
                    translationY = textTranslationY.value
                },
            text = "Alex Uioreanu",
            color = Color.White,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.SemiBold,
            fontSize = 35.sp,
        )
    }
}