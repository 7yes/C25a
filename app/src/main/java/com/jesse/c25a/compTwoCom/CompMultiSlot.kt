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
fun CompMultiSlotScreen() {
    MyComplexLayout(
        header = { Text("This is the header") },
        body = { Text("This is the body") },
        footer = { Text("This is the footer") }
    )
}

@Composable
fun MyComplexLayout(
    header: @Composable () -> Unit,
    body: @Composable () -> Unit,
    footer: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(32.dp)
            .background(Color.Yellow)
    ) {
        header()
        body()
        footer()
    }
}
