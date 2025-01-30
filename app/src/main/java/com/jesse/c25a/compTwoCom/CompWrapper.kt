package com.jesse.c25a.compTwoCom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ComposableWrapperScreen() {
    MyCustomLayout {
        MyText()
    }
}

@Composable
fun MyCustomLayout(content: @Composable () -> Unit) {
    Column (
        modifier = Modifier
            .padding(32.dp)
            .background(Color.LightGray)
    ) {
        content()
    }
}

@Composable
fun MyText() {
    Text(text = "This is some text inside the wrapper.")
}

