package com.training.okei.feature.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.training.okei.R

sealed class NavRouteMain(
    val route: String , @StringRes val name: Int, @DrawableRes val icon: Int
){
    object WorkPerformanceRoute : NavRouteMain("WorkPerformanceRoute" , R.string.work_performance, R.drawable.ic_calendar)


}
val listRoute = listOf<NavRouteMain>(NavRouteMain.WorkPerformanceRoute)


