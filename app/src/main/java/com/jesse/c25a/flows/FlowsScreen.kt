package com.jesse.c25a.flows

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FlowsScreen(viewModel: FlowsVM = hiltViewModel()) {

    val ej1 by viewModel.simpleFlow.collectAsState()
    val ej2 by viewModel.example2.collectAsState()

    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text(text = "Simple Flow $ej1", fontSize = 30.sp)
        Text(text = "Example 2 $ej2", fontSize = 30.sp)
    }
}