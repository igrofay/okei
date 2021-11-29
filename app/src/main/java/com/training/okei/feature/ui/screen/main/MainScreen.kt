package com.training.okei.feature.ui.screen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.skydoves.landscapist.glide.GlideImage
import com.training.okei.data.User
import com.training.okei.feature.main.ViewModelMain
import com.training.okei.feature.navigation.NavigationRouteMain
import com.training.okei.feature.navigation.listRoute
import com.training.okei.feature.ui.screen.main.calendar.CalendarScreen
import com.training.okei.feature.ui.theme.BrushBackgroundApp
import com.training.okei.feature.ui.theme.brushSurface
import com.training.okei.feature.ui.theme.gilroy
import com.training.okei.feature.ui.wiget.ItemDrawer

@Composable
fun MainScreen(modelMain: ViewModelMain) {
    val navHostController = rememberNavController()
    Scaffold(
        Modifier
            .fillMaxSize(),
        drawerContent = {
            DrawerContent(
                modelMain.liveDataUser.value!!,
                navHostController
            )
        }
    ) {
        NavHost(navHostController, NavigationRouteMain.CalendarPlanRoute.route, Modifier.fillMaxSize().background(colors.BrushBackgroundApp)){
            composable(NavigationRouteMain.CalendarPlanRoute.route){
                CalendarScreen()
            }
        }
    }
}

@SuppressLint("ResourceType")
@Composable
fun DrawerContent(
    user: User,
    navHost: NavHostController
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(colors.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .height(170.dp)
                .background(colors.brushSurface)
                .padding(18.dp)
        ) {
            GlideImage( user.imageURL ,
                Modifier
                    .size(80.dp)
                    .shadow(10.dp, CircleShape)
                    .clip(CircleShape)
                    )
            Text(user.name , fontSize = 18.sp , modifier = Modifier.padding(top = 12.dp),
                fontFamily = gilroy , fontWeight =FontWeight.Bold , color = Color.White.copy(0.9f))
            Text(user.status , fontSize = 14.sp , modifier = Modifier , fontFamily = gilroy , fontWeight = FontWeight.Light,
                color = Color.White.copy(0.7f))
        }
        Column(
            Modifier
                .fillMaxSize()
                .padding(18.dp)
        ) {
            val navBackStackEntry by navHost.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            listRoute.forEach {  item ->
                ItemDrawer(currentDestination?.hierarchy?.any { it.route == item.route } == true,
                    onClick = {
                        navHost.navigate(item.route) {
                            popUpTo(navHost.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    labelRes = item.name,
                    iconRes = item.icon
                )
            }
        }
    }
}