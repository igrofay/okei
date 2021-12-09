package com.training.okei.feature.ui.screen.main.workperform.evaluations

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.training.okei.R
import com.training.okei.data.Evaluation
import com.training.okei.feature.ui.theme.Black900
import com.training.okei.feature.ui.theme.Gray200
import com.training.okei.feature.ui.theme.gilroy
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun EvaluationsTeacherScreen(
    listEvaluations: List<Evaluation?>,
    enabledEdit: Boolean,
    nameEval: String,
    onClickPush: ()-> Unit
) {
    Box(Modifier.fillMaxSize()){
        LazyColumn(
            Modifier
                .fillMaxSize()
                .background(colors.primaryVariant),
            contentPadding = PaddingValues(horizontal = 12.dp , 8.dp)
        ){
            item {
                Title()
                if (listEvaluations.isNotEmpty()){
                    listEvaluations[0]?.let {
                        OneItem(it, enabledEdit, nameEval)
                    }

                    listEvaluations[1]?.let {
                        Spacer(Modifier.height(15.dp))
                        TwoItem(it, enabledEdit, nameEval)
                    }

                    listEvaluations[2]?.let {
                        Spacer(Modifier.height(15.dp))
                        Three_ThirteenItem(it, R.string.two_one, enabledEdit, nameEval)
                    }

                    listEvaluations[3]?.let {
                        Spacer(Modifier.height(15.dp))
                        Three_ThirteenItem(it,  R.string.two_two, enabledEdit, nameEval)
                    }


                    listEvaluations[4]?.let {
                        Spacer(Modifier.height(15.dp))
                        Three_ThirteenItem(it,  R.string.two_three, enabledEdit, nameEval)
                    }

                    listEvaluations[5]?.let {
                        Spacer(Modifier.height(15.dp))
                        Three_ThirteenItem(it,  R.string.three_one, enabledEdit, nameEval,2)
                    }

                    listEvaluations[6]?.let {
                        Spacer(Modifier.height(15.dp))
                        Three_ThirteenItem(it,  R.string.three_two, enabledEdit, nameEval)
                    }

                    listEvaluations[7]?.let {
                        Spacer(Modifier.height(15.dp))
                        Three_ThirteenItem(it,  R.string.three_three, enabledEdit, nameEval, 4)
                    }

                    listEvaluations[8]?.let {
                        Spacer(Modifier.height(15.dp))
                        Three_ThirteenItem(it,  R.string.four_one, enabledEdit, nameEval, 2)
                    }

                    listEvaluations[9]?.let {
                        Spacer(Modifier.height(15.dp))
                        Three_ThirteenItem(it,  R.string.four_two, enabledEdit, nameEval, 4)
                    }

                    listEvaluations[10]?.let {
                        Spacer(Modifier.height(15.dp))
                        Three_ThirteenItem(it,  R.string.five_one, enabledEdit, nameEval, 2)
                    }

                    listEvaluations[11]?.let {
                        Spacer(Modifier.height(15.dp))
                        Three_ThirteenItem(it,  R.string.five_two, enabledEdit, nameEval)
                    }

                    listEvaluations[12]?.let {
                        Spacer(Modifier.height(15.dp))
                        Three_ThirteenItem(it,  R.string.five_three, enabledEdit, nameEval, 4)
                    }

                }
                if (enabledEdit){
                    Spacer(Modifier.height(60.dp))
                }
            }
        }
        if(enabledEdit){
            Button(
                onClick = onClickPush,
                colors = ButtonDefaults.buttonColors(colors.secondary),
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.BottomCenter)
                    .height(50.dp),
                shape = RoundedCornerShape(50)
            ) {
                Text("Отправить")
            }
        }
    }
}

@Composable
fun Title() {
    Box(
        Modifier
            .fillMaxWidth(),
        Alignment.Center
    ){
        Text(
            stringResource(R.string.criteria),
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
}

@SuppressLint("SimpleDateFormat")
@Composable
fun OneItem(
    evaluation: Evaluation,
    enabledEdit: Boolean,
    nameEval: String
) {
    val dt = SimpleDateFormat("yyyy-MM-dd HH:mm:ss a")

    var stateVal by remember {
        mutableStateOf(evaluation.countPoints)
    }
    var lastEval by remember {
        mutableStateOf(evaluation.lastEvaluating)
    }
    var lastChangeEval by remember {
        mutableStateOf(evaluation.lastChangeEval)
    }
    val mutValClick
        : (value: Int) -> Unit =
            {
                stateVal = it
                evaluation.countPoints=it

                evaluation.lastEvaluating = nameEval
                lastEval = evaluation.lastEvaluating

                evaluation.lastChangeEval = dt.format(Date())
                lastChangeEval = evaluation.lastChangeEval
            }
    Card(
        Modifier.fillMaxWidth(),
        RoundedCornerShape(15),
        if(isSystemInDarkTheme()) Black900 else Gray200
    ) {
        
        Column(
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("${evaluation.numEval} ${stringResource(R.string.one_one)}")
            Divider(Modifier.padding(vertical = 6.dp))
            Column(
                Modifier
                    .fillMaxWidth()
            ) {
                Text("Оценщик: $lastEval")
                Text("Дата: $lastChangeEval")
            }
            Divider(Modifier.padding(vertical = 6.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Row {
                    RadioButton(
                        selected = stateVal ==1,
                        onClick = {
                            mutValClick(1)
                                  },
                        enabled = enabledEdit
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(stringResource(R.string.low))
                }

                Row {
                    RadioButton(
                        selected = stateVal ==2,
                        onClick = {
                            mutValClick(2)
                                  },
                        enabled = enabledEdit
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(stringResource(R.string.average))
                }

                Row {
                    RadioButton(
                        selected = stateVal ==3,
                        onClick = {
                            mutValClick(3)
                                  },
                        enabled = enabledEdit
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(stringResource(R.string.high))
                }
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun TwoItem(
    evaluation: Evaluation,
    enabledEdit: Boolean,
    nameEval: String
) {
    val dt = SimpleDateFormat("yyyy-MM-dd hh:mm:ss a")
    var stateVal by remember {
        mutableStateOf(evaluation.countPoints)
    }
    var lastEval by remember {
        mutableStateOf(evaluation.lastEvaluating)
    }
    var lastChangeEval by remember {
        mutableStateOf(evaluation.lastChangeEval)
    }
    val mutValClick
            : (value: Int) -> Unit =
        {
            stateVal = it
            evaluation.countPoints=it

            evaluation.lastEvaluating = nameEval
            lastEval = evaluation.lastEvaluating

            evaluation.lastChangeEval = dt.format(Date())
            lastChangeEval = evaluation.lastChangeEval
        }
    Card(
        Modifier.fillMaxWidth(),
        RoundedCornerShape(10),
        if(isSystemInDarkTheme()) Black900 else Gray200
    ) {

        Column(
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("${evaluation.numEval} ${stringResource(R.string.one_two)}")
            Spacer(Modifier.height(4.dp))
            Text(stringResource(R.string.one_two_ex), fontSize = 14.sp)
            Divider(Modifier.padding(vertical = 6.dp))
            Column(
                Modifier
                    .fillMaxWidth()
            ) {
                Text("Оценщик: $lastEval")
                Text("Дата: $lastChangeEval")
            }
            Divider(Modifier.padding(vertical = 6.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Row {
                    RadioButton(
                        selected = stateVal ==1,
                        onClick = { mutValClick(1) },
                        enabled = enabledEdit
                    )
                    Spacer(Modifier.width(4.dp))
                    Text("1")
                }

                Row {
                    RadioButton(
                        selected = stateVal == 2,
                        onClick = { mutValClick(2) },
                        enabled = enabledEdit
                    )
                    Spacer(Modifier.width(4.dp))
                    Text("2")
                }

                Row {
                    RadioButton(
                        selected = stateVal ==3,
                        onClick = { mutValClick(3) },
                        enabled = enabledEdit
                    )
                    Spacer(Modifier.width(4.dp))
                    Text("3")
                }
                Row {
                    RadioButton(
                        selected = stateVal ==4,
                        onClick = { mutValClick(4) },
                        enabled = enabledEdit
                    )
                    Spacer(Modifier.width(4.dp))
                    Text("4")
                }
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun Three_ThirteenItem(
    evaluation: Evaluation,
    @StringRes text: Int,
    enabledEdit: Boolean,
    nameEval: String,
    maxVal: Int = 3
) {
    val dt = SimpleDateFormat("yyyy-MM-dd hh:mm:ss a")
    var stateVal by remember {
        mutableStateOf(evaluation.countPoints)
    }
    var lastEval by remember {
        mutableStateOf(evaluation.lastEvaluating)
    }
    var lastChangeEval by remember {
        mutableStateOf(evaluation.lastChangeEval)
    }
    val mutValClick
            : (value: Int) -> Unit =
        {
            stateVal = it
            evaluation.countPoints=it

            evaluation.lastEvaluating = nameEval
            lastEval = evaluation.lastEvaluating

            evaluation.lastChangeEval = dt.format(Date())
            lastChangeEval = evaluation.lastChangeEval
        }
    Card(
        Modifier.fillMaxWidth(),
        RoundedCornerShape(15),
        if(isSystemInDarkTheme()) Black900 else Gray200
    ) {

        Column(
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("${evaluation.numEval} ${stringResource(text)}")
            Divider(Modifier.padding(vertical = 6.dp))
            Column(
                Modifier
                    .fillMaxWidth()
            ) {
                Text("Оценщик: $lastEval")
                Text("Дата: $lastChangeEval")
            }
            Divider(Modifier.padding(vertical = 6.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Row {
                    RadioButton(
                        selected = stateVal ==0,
                        onClick = { mutValClick(0) },
                        enabled = enabledEdit
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(stringResource(R.string.no))
                }

                Row {
                    RadioButton(
                        selected = stateVal == maxVal,
                        onClick = { mutValClick(maxVal) },
                        enabled = enabledEdit
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(stringResource(R.string.yes))
                }
            }
        }
    }
}
