package com.training.okei.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.ui.Modifier
import com.training.okei.feature.navigation.NavigationApp
import com.training.okei.feature.ui.theme.BrushBackgroundApp

import com.training.okei.feature.ui.theme.OkeiTheme

class MainActivity : ComponentActivity() {
    private val model: ViewModelMain by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OkeiTheme {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(colors.BrushBackgroundApp)
                ){
                    NavigationApp(model)
                }
            }
        }
    }


}
