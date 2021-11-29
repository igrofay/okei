package com.training.okei.feature.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val Blue700 = Color(0xFF00569D)
val Blue500 = Color(0xFF38BAFE)
val Black900 = Color(0xFF202020)
val Gray200 =  Color(0xFFE8E8EE)

val Colors.BrushBackgroundApp: Brush
    get(){
        val listColors =
            if (isLight) listOf(Blue500, Blue700)
            else listOf(Blue700, Color.Black)
        return Brush.verticalGradient(listColors)
    }

val Colors.brushSurface : Brush
    get() {
        val listColors =
            if (isLight) listOf(Blue700 , Blue500 )
            else listOf(Color.Black, Blue700)
        return Brush.linearGradient(listColors)
    }

val Colors.colorItem : Color
    get() = if (isLight) Blue700 else Blue500

val Colors.blackOrWhile : Color
    get() = if (isLight) Color.Black else Color.White