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
    statusBox: StatusBox
) {
    Box(
        Modifier
            .size(size)
            .border(1.dp, color , RoundedCornerShape(15))
            .padding(4.dp),
        Alignment.Center
    ){
        statusBox.icon?.let {
            Icon(painterResource(it),null , Modifier.fillMaxSize(), color)
        }
    }
}

enum class StatusBox(@DrawableRes val icon: Int?) {
    Underway(R.drawable.ic_underway),
    Passed(R.drawable.ic_passed),
    Null(null)
}