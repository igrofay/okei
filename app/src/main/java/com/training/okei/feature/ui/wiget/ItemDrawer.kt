package com.training.okei.feature.ui.wiget

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.training.okei.feature.ui.theme.blackOrWhile
import com.training.okei.feature.ui.theme.colorItem
import com.training.okei.feature.ui.theme.gilroy

@Composable
fun ItemDrawer(
    selected: Boolean,
    onClick: () -> Unit,
    @StringRes labelRes: Int,
    @DrawableRes iconRes:  Int
) {
    val background = if (selected) colors.colorItem.copy(0.2f) else Color.Transparent
    val contentColor = if(selected) colors.colorItem else  colors.blackOrWhile
    Row(
        Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(background, RoundedCornerShape(15))
            .clickable (onClick = onClick)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        Icon(painterResource(iconRes), null ,
            Modifier.size(30.dp) , tint = contentColor)
        Text(stringResource(labelRes), color = contentColor , fontFamily = gilroy , fontWeight = FontWeight.Bold)
    }
}