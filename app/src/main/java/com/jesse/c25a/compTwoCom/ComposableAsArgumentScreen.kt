package com.jesse.c25a.compTwoCom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnyComposable(
    anyComposable: @Composable () -> Unit
) {
    anyComposable()
}

@Composable
fun ComposableAsArgumentScreen() {
    // big red Box composable
    val composable1 = @Composable {
        Box (modifier = Modifier.size(200.dp).background(Color.Red))
    }

    // Text composable
    val composable2 = @Composable { Text(text = "Hola") }

    // small green rectangular Box composable
    val composable3 = @Composable {
        Box(modifier = Modifier.size(width = 100.dp, height = 30.dp)
                .background(Color.Green)) }

    var dynamicComposable by remember { mutableStateOf<@Composable () -> Unit> (@Composable { composable1() }) }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                dynamicComposable = composable1
            }) {
                Text("Comp 1")
            }

            Button(onClick = {
                dynamicComposable = composable2
            }) {
                Text("Comp 2")
            }

            Button(onClick = {
                dynamicComposable = composable3
            }) {
                Text("Comp 3")
            }
        }
        AnyComposable(anyComposable = dynamicComposable)
    }
}

