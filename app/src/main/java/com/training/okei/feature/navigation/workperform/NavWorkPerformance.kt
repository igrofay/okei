package com.training.okei.feature.navigation.workperform

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.training.okei.data.Status
import com.training.okei.feature.ui.screen.main.workperform.calendar.CalendarScreen
import com.training.okei.feature.ui.screen.main.workperform.teachers.TeachersScreen

@Composable
fun NavWorkPerformance(
    model: ViewModelWorkPerformance = viewModel()
) {
    val nav = rememberNavController()
    NavHost(nav,NavRouteWorkPerformance.CalendarRoute.route ){
        composable( NavRouteWorkPerformance.CalendarRoute.route){
            val listMonths = remember {
                model.stateListMonths
            }
            CalendarScreen(listMonths){
                if (
                    Status.Teacher.nameStatus == model.liveDataUser.value!!.status
                ){
                    nav.navigate( NavRouteWorkPerformance.EvaluationsTeacherRoute
                        .getAllRout(it, model.liveDataUser.value!!.login)
                    )
                }else{
                    nav.navigate( NavRouteWorkPerformance.TeachersRoute.getAllRoute(it))
                }
            }

            LaunchedEffect(true){
                model.getListMonths()
            }
        }
        composable(
            NavRouteWorkPerformance.TeachersRoute.getAllRoute(),
            arguments = listOf(navArgument(NavRouteWorkPerformance.TeachersRoute.argument1) { type = NavType.StringType })
        ){backStackEntry ->
            val nameMonth = backStackEntry.arguments?.getString(
                NavRouteWorkPerformance.TeachersRoute.argument1
            )
            val listTeachers = remember {
                model.stateListTeachers
            }
            Log.e("sss" , listTeachers.size.toString())
            TeachersScreen(
                listTeachers
            ){
                nav.navigate( NavRouteWorkPerformance.EvaluationsTeacherRoute
                    .getAllRout(nameMonth!! , it)
                )
            }

            LaunchedEffect(true ){
                model.getListTeachers(nameMonth!!)
            }
        }
        composable(
            NavRouteWorkPerformance.EvaluationsTeacherRoute.getAllRout(),
            arguments = listOf(
                navArgument(NavRouteWorkPerformance.EvaluationsTeacherRoute.argument1) { type = NavType.StringType },
                navArgument(NavRouteWorkPerformance.EvaluationsTeacherRoute.argument2) { type = NavType.StringType }
            )
        ){backStackEntry ->
            val nameMonth = backStackEntry.arguments?.getString(
                NavRouteWorkPerformance.EvaluationsTeacherRoute.argument1
            )
            val loginTeacher = backStackEntry.arguments?.getString(
                NavRouteWorkPerformance.EvaluationsTeacherRoute.argument2
            )

        }
    }

}



sealed class NavRouteWorkPerformance(val route : String){
    object CalendarRoute : NavRouteWorkPerformance("CalendarRoute")
    object TeachersRoute: NavRouteWorkPerformance("TeachersRoute"){
        const val argument1 = "nameMonth"
        fun getAllRoute(arg: String = "{$argument1}") : String{
            return "${route}/${arg}"
        }
    }
    object EvaluationsTeacherRoute : NavRouteWorkPerformance("EvaluationsTeacherRoute"){
        const val argument1 = "nameMonth"
        const val argument2 = "loginTeacher"
        fun getAllRout(
            arg1: String = "{${argument1}}",
            arg2: String = "{${argument2}}",
        ) : String{
            return "$route/$arg1/$arg2"
        }
    }
}