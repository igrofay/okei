package com.training.okei.feature.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import com.training.okei.R
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.training.okei.feature.ui.theme.BrushBackgroundApp

@Composable
fun SplashScreen() {
    BoxWithConstraints(
        Modifier
            .fillMaxSize()
            .background(colors.BrushBackgroundApp),
        Alignment.Center
    ) {
        val height = maxHeight
        val weight = maxWidth
        val size = (if (height> weight) weight else height) /2

        Card(
            Modifier
                .size(size),
            backgroundColor = Color.White,
            shape = RoundedCornerShape(25)
        ) {
            Image(painterResource(R.drawable.ic_logo),
                null, Modifier.fillMaxSize()
            )
        }
    }
}