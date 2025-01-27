package com.jesse.c25a.twocomposable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun TwoComposableScreen() {
    var show by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        My2ndComposable(show, email) {
            email = it
        }
        Button(onClick = { show = !show }) { Text(text = "Switch") }
    }
}

@Composable
fun My2ndComposable(show: Boolean, email: String, newEmail: (String) -> Unit) {
    if (show) Text(text = email)
    else TextField(
        onValueChange = { newEmail(it) },
        value = email,
        label = { Text(text = "Email") },
        placeholder = { Text(text = "Set your email") },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    )
}





