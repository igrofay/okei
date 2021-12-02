package com.training.okei.feature.navigation

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.training.okei.data.UserAuthData
import com.training.okei.data.repositories.UserRepository
import com.training.okei.feature.main.ViewModelMain
import com.training.okei.feature.ui.screen.main.MainScreen
import com.training.okei.feature.ui.screen.signin.SignInScreen
import com.training.okei.feature.ui.screen.splash.SplashScreen
import com.training.okei.module.workmanager.WorkAuthorization

enum class NavRouteApp {
    Splash,
    SignIn,
    Main
}


@Composable
fun ComponentActivity.NavApp(modelMain: ViewModelMain) {
    val nav = rememberNavController()
    val user by modelMain.liveDataUser.observeAsState()

    if (user != null && nav.currentBackStackEntry?.id != null) {
        nav.navigate(NavRouteApp.Main.name)
    }
    val needSignIn = remember {
        modelMain.needSignIn
    }
    if (needSignIn.value) nav.navigate(NavRouteApp.SignIn.name)
    NavHost(nav, NavRouteApp.Splash.name) {
        composable(NavRouteApp.Splash.name) {
            SplashScreen()
            LaunchedEffect(true){
                WorkAuthorization.start(this@NavApp)
                if (user != null){
                    nav.navigate(NavRouteApp.Main.name)
                }
            }
        }
        composable(NavRouteApp.SignIn.name) {
            SignInScreen { login, password ->
                UserRepository.saveDataAut(
                    Gson().toJson(UserAuthData(login, password))
                )
                modelMain.needSignIn.value = false
                nav.popBackStack()
            }
            BackHandler {
                this@NavApp.finish()
            }
        }
        composable(NavRouteApp.Main.name) {
            MainScreen(modelMain)
            BackHandler {
                this@NavApp.finish()
            }
        }
    }
}



