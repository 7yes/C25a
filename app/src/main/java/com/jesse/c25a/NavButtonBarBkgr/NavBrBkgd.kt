package com.jesse.c25a.NavButtonBarBkgr

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat

@Composable
fun NavButtonBarBackgroundScreen(modifier: Modifier = Modifier) {
    val view = LocalView.current
    val window = (view.context as? Activity)?.window

    // State to track if the color should be yellow or reverted
    var isNavBarYellow by remember { mutableStateOf(false) }
    // Store the original nav bar color to revert
    var originalNavBarColor by remember { mutableStateOf<Int?>(null) }
    var originalNavBarLightAppearance by remember { mutableStateOf<Boolean?>(null) }


    // Effect to capture the original navigation bar color when the composable enters
    // and to revert when it leaves (optional, but good practice)
    DisposableEffect(window) {
        if (window != null) {
            originalNavBarColor = window.navigationBarColor

            // Capture original light/dark appearance for icons
            val windowInsetsController = WindowCompat.getInsetsController(window, view)
            originalNavBarLightAppearance = windowInsetsController.isAppearanceLightNavigationBars
        }
        onDispose {
            // Revert to original color and appearance when the composable is disposed
            if (window != null && originalNavBarColor != null) {
                window.navigationBarColor = originalNavBarColor!!
                originalNavBarLightAppearance?.let {
                    WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = it
                }
            }
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding(), // Important: Respects system insets including navigation bar
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            window?.let {
                if (!isNavBarYellow) {
                    // If not already yellow, change to yellow
                    if (originalNavBarColor == null) { // Capture if not already captured
                        originalNavBarColor = it.navigationBarColor
                        val controller = WindowCompat.getInsetsController(it, view)
                        originalNavBarLightAppearance = controller.isAppearanceLightNavigationBars
                    }
                    it.navigationBarColor = androidx.compose.ui.graphics.Color.Yellow.toArgb()
                    // When nav bar is yellow (light), navigation icons should be dark for contrast
                    WindowCompat.getInsetsController(it, view).isAppearanceLightNavigationBars = true
                    isNavBarYellow = true
                } else {
                    // If already yellow, revert to original (or a default)
                    originalNavBarColor?.let { color ->
                        it.navigationBarColor = color
                    }
                    originalNavBarLightAppearance?.let { isLight ->
                        WindowCompat.getInsetsController(it, view).isAppearanceLightNavigationBars = isLight
                    }
                    isNavBarYellow = false
                }
            }
        }) {
            Text(if (isNavBarYellow) "Revert Nav Bar Color" else "Set Nav Bar to Yellow")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text("Content above navigation bar")
    }
}
