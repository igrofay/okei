package com.training.okei.feature.ui.wiget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.training.okei.feature.ui.theme.gilroy

@Composable
fun CircularProgressBar(
    percentage: Float,
    color: Color,
    fontSize: TextUnit = 14.sp,
    radius: Dp = 30.dp,
    strokeWidth : Dp = 2.dp
) {
    Box(
        Modifier
            .size(radius*2),
        Alignment.Center
    ){
        Canvas(
            Modifier.fillMaxSize()
        ){
            drawArc(
                color,
                -90f,
                3.6f*percentage,
                false,
                style = Stroke(strokeWidth.toPx() , cap = StrokeCap.Round)
            )
        }
        Text("$percentage%" , fontSize = fontSize ,
            fontFamily = gilroy , fontWeight = FontWeight.Light , color = Color.Black)
    }
}
