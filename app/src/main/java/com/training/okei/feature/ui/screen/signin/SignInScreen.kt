package com.training.okei.feature.ui.screen.signin

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.training.okei.R
import com.training.okei.feature.app.toast
import com.training.okei.feature.ui.theme.gilroy


@Composable
fun SignInScreen(
    onClick: (login: String, password: String)->Unit
) {
    val isVertical = with(LocalContext.current){
        resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    }
    if (isVertical) VerticalOrientation(onClick)
    else HorizontalOrientation(onClick)
}

@Composable
fun HorizontalOrientation(
    onClick: (login: String, password: String)->Unit
) {
    Row(
        Modifier.fillMaxSize()
    ) {
        Box(
            Modifier
                .fillMaxHeight()
                .weight(1f),
            contentAlignment = Alignment.Center
        ){
            IconHorizontal()
        }
        Column(
            Modifier
                .weight(1f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InputData(onClick)
        }
    }
}

@Composable
private fun VerticalOrientation(
    onClick: (login: String, password: String)->Unit
) {
    Column(
        Modifier.fillMaxSize()
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ){
            IconVertical()
        }
        Column(
            Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InputData(onClick)
        }
    }
}

@Composable
private fun BoxScope.IconHorizontal() {
    Box(
        Modifier
            .fillMaxSize()
            .scale(1f, 1f)
            .background(
                Color.White,
                RoundedCornerShape(0, 0, 80, 0)
            )
    )
    Image(painterResource(R.drawable.ic_statistics), null,
        Modifier
            .fillMaxSize(0.8f)
            .padding(16.dp)
            .align(Alignment.TopStart))
}

@Composable
private fun IconVertical() {
    Box(
        Modifier
            .fillMaxSize()
            .scale(1.25f, 1f)
            .background(
                Color.White,
                RoundedCornerShape(0, 0, 100, 100)
            )
    )
    Image(painterResource(R.drawable.ic_statistics), null,
        Modifier
            .fillMaxSize(0.7f)
            )
}

@Composable
private fun ColumnScope.InputData(onClick: (login: String, password: String)->Unit) {
    var login by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    val colorsEditText = TextFieldDefaults.outlinedTextFieldColors(
        textColor = Color.White,
        focusedBorderColor = Color.White.copy(0.9f),
        focusedLabelColor = Color.White.copy(0.9f),
        cursorColor = Color.White.copy(0.9f),
        unfocusedBorderColor = Color.White.copy(0.7f),
        unfocusedLabelColor = Color.White.copy(0.7f)
    )
    val radius = RoundedCornerShape(25)
    Spacer(Modifier.weight(0.3f))
    OutlinedTextField(
        value = login,
        onValueChange = {login = it},
        label = { Text(stringResource(R.string.login), fontFamily = gilroy)},
        singleLine = true,
        colors = colorsEditText,
        shape = radius,
        modifier = Modifier
            .fillMaxWidth(0.7f)
    )
    Spacer(Modifier.weight(0.1f))
    OutlinedTextField(
        value = password,
        onValueChange = {password = it },
        label = { Text(stringResource(R.string.password), fontFamily = gilroy)},
        singleLine = true,
        colors = colorsEditText,
        shape = radius,
        modifier = Modifier
            .fillMaxWidth(0.7f)
    )
    Spacer(Modifier.weight(0.3f))
    Button(
        modifier = Modifier
            .fillMaxWidth(0.7f),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        shape = radius,
        contentPadding = PaddingValues(18.dp),
        onClick = {
            if (login.isNotBlank() && password.isNotBlank()){
                onClick(login, password)
            }else{
                toast(R.string.enter_data)
            }

        }
    ) {
        Text(stringResource(R.string.sign_in), color = Color.Black, fontFamily = gilroy, fontSize = 16.sp)
    }
    Spacer(Modifier.weight(0.3f))
    val context = LocalContext.current
    ClickableText(
        text = AnnotatedString(stringResource(R.string.advertisement)),
        style = TextStyle(Color.White,12.sp, FontWeight.W100,
            textAlign = TextAlign.Center, textDecoration = TextDecoration.Underline
        ),
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(bottom = 12.dp),
        onClick = {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://oksei.ru/")
                )
            )
        }
    )

}