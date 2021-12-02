package com.training.okei.feature.ui.screen.main.workperform.calendar

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import com.training.okei.data.Month
import com.training.okei.feature.ui.theme.Blue700
import com.training.okei.feature.ui.theme.gilroy
import com.training.okei.feature.ui.wiget.CircularProgressBar
import com.training.okei.feature.ui.wiget.StatusBox

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalendarScreen(
    list: List<Month>,
    onClickItem : (nameMonth: String)-> Unit
) {
    val fixed = with(LocalContext.current){
        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 1
        else 2
    }
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val stateList = rememberLazyListState()
        Box(
            Modifier
                .fillMaxWidth(),
            Alignment.Center
        ){
            Text(
                stringResource(R.string.work_performance),
                Modifier
                    .padding(16.dp)
                    .border(1.dp, Color.White, RoundedCornerShape(100))
                    .padding(12.dp),
                fontFamily = gilroy,
                fontWeight = FontWeight.Light,
                color = Color.White,
                fontSize = 18.sp
            )
        }
        LazyVerticalGrid(
            cells = GridCells.Fixed(fixed),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(),
            state = stateList
        ){
            items(list){
                ItemMonth(it , onClickItem)
            }
        }
    }
}

//TODO Принимать объект
@Composable
fun ItemMonth(month: Month,onClickItem : (nameMonth: String)-> Unit) {
    Row(
        Modifier
            .padding(vertical = 12.dp, horizontal = 8.dp)
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.White, RoundedCornerShape(15))
            .clickable {
                onClickItem(month.nameMonth)
            }
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        StatusBox(size = 35.dp, color = colors.onPrimary, underway =  month.underway)
        Column(
            Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            Text(month.nameMonth , fontFamily = gilroy , fontWeight = FontWeight.Light , fontSize = 16.sp, color = Color.Black)
            Text(text = "Последние измение: ${month.lastChange}", fontFamily = gilroy , fontWeight = FontWeight.Light ,
                fontSize = 10.sp, color = Color.Black)
            Text(text = month.leader, fontFamily = gilroy , fontWeight = FontWeight.Light , fontSize = 10.sp, color = Color.Black)
        }
        CircularProgressBar(month.progress, Blue700)
    }
}

