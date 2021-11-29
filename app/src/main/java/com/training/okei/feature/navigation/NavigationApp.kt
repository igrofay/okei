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
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.google.gson.Gson
import com.training.okei.data.UserAuthData
import com.training.okei.data.UserRepository
import com.training.okei.feature.main.ViewModelMain
import com.training.okei.feature.ui.screen.main.MainScreen
import com.training.okei.feature.ui.screen.signin.SignInScreen
import com.training.okei.feature.ui.screen.splash.SplashScreen
import com.training.okei.module.workmanager.WorkAuthorization

enum class NavigationRouteApp {
    Splash,
    SignIn,
    Main
}


@Composable
fun ComponentActivity.NavigationApp(modelMain: ViewModelMain) {
    val nav = rememberNavController()
    val user by modelMain.liveDataUser.observeAsState()
    if (user != null && nav.currentBackStackEntry?.id != null) {
        nav.navigate(NavigationRouteApp.Main.name)
    }
    val needSignIn = remember {
        modelMain.needSignIn
    }
    if (needSignIn.value) nav.navigate(NavigationRouteApp.SignIn.name)
    NavHost(nav, NavigationRouteApp.Splash.name) {
        composable(NavigationRouteApp.Splash.name) {
            SplashScreen()
            LaunchedEffect(true){
                WorkAuthorization.start(this@NavigationApp)
                if (user != null){
                    nav.navigate(NavigationRouteApp.Main.name)
                }
            }

        }
        composable(NavigationRouteApp.SignIn.name) {
            SignInScreen { login, password ->
                UserRepository.saveDataAut(
                    Gson().toJson(UserAuthData(login, password))
                )
                modelMain.needSignIn.value = false
                nav.popBackStack()
            }
            BackHandler {
                this@NavigationApp.finish()
            }
        }
        composable(NavigationRouteApp.Main.name) {
            MainScreen(modelMain)
            BackHandler {
                this@NavigationApp.finish()
            }
        }
    }
}



