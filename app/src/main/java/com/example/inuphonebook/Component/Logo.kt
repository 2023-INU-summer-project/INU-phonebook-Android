package com.example.inuphonebook.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp

@Composable
fun Logo(
    size : Dp,
    logoIcon : Int,
    colorFilter : ColorFilter? = null
){
    Image(
        modifier = Modifier.size(size),
        painter = painterResource(logoIcon),
        contentDescription = "Logo Image",
        colorFilter = colorFilter
    )
}