package com.jesse.c25a

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jesse.c25a.burger.presentation.InitialScreen
import com.jesse.c25a.datastore.DataStoreScreen
import com.jesse.c25a.filter.presentation.FilterScreen
import com.jesse.c25a.flows.FlowsScreen
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
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = MyScreens.BaseScreen.name
                ) {
                    composable(MyScreens.BaseScreen.name) {
                        BaseScreen() {
                            Log.d("TAJ", "onCreate: $it ")
                            navController.navigate(it)
                        }
                    }
                    composable(MyScreens.Burger.name) {
                        InitialScreen()
                    }
                    composable(MyScreens.Perritos.name) {
                        PerritosScreen()
                    }
                    composable(MyScreens.Filter.name) {
                        FilterScreen()
                    }
                    composable(MyScreens.DataStore.name) {
                        DataStoreScreen()
                    }
                    composable(MyScreens.Flows.name) {
                        FlowsScreen()
                    }
                }
            }
        }
    }

    enum class MyScreens {
        BaseScreen, Burger, Perritos, Filter, DataStore, Flows
    }

    @Composable
    fun BaseScreen(onclick: (String) -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            var buttons = ""
            MyScreens.entries.forEach {
                if (it != MyScreens.BaseScreen)
                    buttons += "${it.name} "
            }
            val buttonsChuncked = buttons.split(" ").chunked(3)
            buttonsChuncked.forEach {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    it.forEach {
                        if (it != "")
                            Button(onClick = { onclick(it) }) {
                                Text(text = it)
                            }
                    }
                }
            }
        }
    }
}
