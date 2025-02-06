package com.jesse.c25a.compTwoCom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jesse.c25a.R

@Composable
fun dpToSp(dp: Dp): TextUnit = with(LocalDensity.current) { dp.toSp() }

@Composable
fun MyText(text: String, textSizeDp: Dp = 16.dp, style: TextStyle = TextStyle.Default, color: Int = R.color.white) {
    Text(
        text = text,
        fontSize = dpToSp(textSizeDp),
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp,
        style = style,
        color = colorResource(id = color)
    )
}

@Composable
fun ComposableWrapperScreen() {
    MyCustomLayout {
        MyText( text = "Prueba")
    }
}

@Composable
fun MyCustomLayout(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .padding(32.dp)
            .background(Color.LightGray)
    ) {
        content()
    }
}


