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
import com.training.okei.feature.ui.screen.main.workperform.evaluations.EvaluationsTeacherScreen
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
                    model.stateListMonths.forEach{ month ->
                        if(month.nameMonth == it){
                            nav.navigate( NavRouteWorkPerformance.EvaluationsTeacherRoute
                                .getAllRout(it , model.liveDataUser.value!!.login, (month.underway ?:false).toString())
                            )
                        }
                    }
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

            TeachersScreen(
                listTeachers
            ){
                model.stateListMonths.forEach{ month ->
                    if(month.nameMonth == nameMonth){
                        nav.navigate( NavRouteWorkPerformance.EvaluationsTeacherRoute
                            .getAllRout(nameMonth , it, (month.underway ?:false).toString())
                        )
                    }
                }
            }

            LaunchedEffect(true ){
                model.getListTeachers(nameMonth!!)
            }
        }
        composable(
            NavRouteWorkPerformance.EvaluationsTeacherRoute.getAllRout(),
            arguments = listOf(
                navArgument(NavRouteWorkPerformance.EvaluationsTeacherRoute.argument1) { type = NavType.StringType },
                navArgument(NavRouteWorkPerformance.EvaluationsTeacherRoute.argument2) { type = NavType.StringType },
                navArgument(NavRouteWorkPerformance.EvaluationsTeacherRoute.argument3) {type = NavType.BoolType}
            )
        ){backStackEntry ->
            val nameMonth = backStackEntry.arguments?.getString(
                NavRouteWorkPerformance.EvaluationsTeacherRoute.argument1
            )

            val loginTeacher = backStackEntry.arguments?.getString(
                NavRouteWorkPerformance.EvaluationsTeacherRoute.argument2
            )

            val underway =  backStackEntry.arguments?.getBoolean(
                NavRouteWorkPerformance.EvaluationsTeacherRoute.argument3
            ) ?: false
            val listEvaluations = remember {
                model.stateListEvaluations
            }
            LaunchedEffect(true ){
                model.getTeacherPerformance(nameMonth!! ,loginTeacher!!)
            }

            EvaluationsTeacherScreen(
                listEvaluations,
                model.liveDataUser.value!!.status == Status.Evaluating.nameStatus && underway,
                model.liveDataUser.value!!.name
            ){
                model.pushChanges(
                    nameMonth!!,   loginTeacher!! , listEvaluations
                )
            }

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
        const val argument3 = "underway"
        fun getAllRout(
            arg1: String = "{${argument1}}",
            arg2: String = "{${argument2}}",
            arg3: String = "{${argument3}}"
        ) : String{
            return "$route/$arg1/$arg2/$arg3"
        }
    }
}