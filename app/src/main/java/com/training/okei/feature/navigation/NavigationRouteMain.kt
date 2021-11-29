package com.training.okei.feature.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.training.okei.R

sealed class NavigationRouteMain(
    val route: String , @StringRes val name: Int, @DrawableRes val icon: Int
){
    object CalendarPlanRoute : NavigationRouteMain("CalendarPlanRoute" , R.string.calendar_plan, R.drawable.ic_calendar)


}

val listRoute = listOf<NavigationRouteMain>(NavigationRouteMain.CalendarPlanRoute)


