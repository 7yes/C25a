package com.jesse.c25a

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jesse.c25a.perritos.PerritosScreen
import com.jesse.c25a.ui.theme.C25aTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            C25aTheme {
                //InitialScreen()
                PerritosScreen()
            }
        }
    }
}



