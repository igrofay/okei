package com.training.okei.feature.ui.screen.main.workperform.teachers

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.training.okei.R
import com.training.okei.data.Teacher
import com.training.okei.feature.ui.screen.main.workperform.calendar.ItemMonth
import com.training.okei.feature.ui.theme.gilroy
import com.training.okei.feature.ui.theme.whileOrBlack


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TeachersScreen(
    list: List<Teacher>,
    onClickItem: (login: String)->Unit
) {
    val fixed = with(LocalContext.current){
        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 1
        else 2
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(colors.whileOrBlack),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val stateList = rememberLazyListState()
        Box(
            Modifier
                .fillMaxWidth(),
            Alignment.Center
        ) {
            Text(
                stringResource(R.string.lists_teacher),
                Modifier
                    .padding(16.dp)
                    .border(1.dp, colors.primary, RoundedCornerShape(100))
                    .padding(12.dp),
                fontFamily = gilroy,
                fontWeight = FontWeight.Light,
                color = colors.primary,
                fontSize = 18.sp
            )
        }
        //TODO Добавить поиск
        LazyVerticalGrid(
            cells = GridCells.Fixed(fixed),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(),
            state = stateList
        ){
            items(list){
                ItemTeacher(it, onClickItem)

            }
        }
    }
}

@Composable
fun ItemTeacher(teacher: Teacher, onClickItem: (login: String) -> Unit) {
    Row(
        Modifier
            .padding(vertical = 12.dp, horizontal = 8.dp)
            .fillMaxWidth()
            .height(100.dp)
            .background(colors.primary, RoundedCornerShape(15))
            .clickable {
                onClickItem(
                    teacher.login
                )
            }
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Column {
            Text(teacher.name , fontFamily = gilroy , fontWeight = FontWeight.Light ,
                fontSize = 18.sp, color = colors.whileOrBlack)
            Text(teacher.lastChangeTeacher, fontFamily = gilroy , fontWeight = FontWeight.Light ,
                fontSize = 10.sp, color = colors.whileOrBlack)
        }
        Box(
            Modifier
                .padding(horizontal = 4.dp)
                .size(60.dp)
                .border(1.dp , colors.whileOrBlack ,CircleShape),
            Alignment.Center
        ){
            Text(teacher.countPoints.toString() , fontFamily = gilroy ,
                fontWeight = FontWeight.Light , fontSize = 22.sp,
                color = colors.whileOrBlack)
        }
    }
}