package com.training.okei.feature.ui.wiget

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.training.okei.R

@Composable
fun StatusBox(
    size: Dp,
    color: Color,
    underway: Boolean?
) {
    Box(
        Modifier
            .size(size)
            .border(1.dp, color , RoundedCornerShape(15))
            .padding(4.dp),
        Alignment.Center
    ){
        underway?.let {
            val icon = if (it) R.drawable.ic_underway else R.drawable.ic_passed
            Icon(painterResource(icon),null , Modifier.fillMaxSize(), color)
        }
    }
}

