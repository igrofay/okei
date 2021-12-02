package com.training.okei.feature.ui.screen.main.workperform.evaluations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.training.okei.R
import com.training.okei.data.Evaluation
import com.training.okei.feature.ui.theme.Gray200

@Composable
fun EvaluationsTeacherScreen(

) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(colors.primaryVariant)
    ){

    }
}


@Composable
fun OneItem(
    evaluation: Evaluation
) {
    Card(
        Modifier.fillMaxWidth(),
        RoundedCornerShape(25),
        Gray200
    ) {
        Column(Modifier.fillMaxWidth()) {
            Text("${evaluation.numEval} ${stringResource(R.string.one_one)}")
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Row {
                    RadioButton(
                        selected = evaluation.countPoints ==1,
                        onClick = { /*TODO*/ }
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(stringResource(R.string.low))
                }

                Row {
                    RadioButton(
                        selected = evaluation.countPoints ==2,
                        onClick = { /*TODO*/ }
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(stringResource(R.string.average))
                }

                Row {
                    RadioButton(
                        selected = evaluation.countPoints ==3,
                        onClick = { /*TODO*/ }
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(stringResource(R.string.high))
                }
            }
        }
    }
}