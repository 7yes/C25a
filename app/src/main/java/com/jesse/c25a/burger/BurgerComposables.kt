package com.jesse.c25a.burger

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.jesse.c25a.burger.domain.model.SmallItem
import com.jesse.c25a.burger.presentation.SmallViewModel
import com.jesse.c25a.burger.presentation.uiState.UIState

@Composable
fun InitialScreen(mainViewModel: SmallViewModel = hiltViewModel()) {
    val data by mainViewModel.uiState.collectAsState()
    lateinit var itemSelected: SmallItem
    val navController = rememberNavController()
    val navHost = NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(data){
                itemSelected = it
                navController.navigate("details")
            }
        }
        composable("details") {
            DetailsScreen(itemSelected)
        }
    }
}

@Composable
fun DetailsScreen(itemSelected: SmallItem) {
    Column (modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        AsyncImage(
            model = itemSelected.image,
            contentDescription = null,
            modifier = Modifier.size(300.dp)
        )
        Text(text = itemSelected.name ?: "Name not Avaliable")
        Text(text = itemSelected.age ?: "Age not Avaliable")
    }
}

@Composable
fun HomeScreen(data: UIState, onItemClicked: (SmallItem) -> Unit) {
    when (data) {
        is UIState.Success -> {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(data.mySuccessList.size) {
                    ListItem(modifier = Modifier.padding(8.dp).clickable {
                        onItemClicked(data.mySuccessList[it])
                    }, tonalElevation = 10.dp,
                        shadowElevation = 10.dp,
                        headlineContent = { Text(text = data.mySuccessList[it].name ?: "") },
                        supportingContent = { Text(text = data.mySuccessList[it].url ?: "") },
                        overlineContent = {
                            Text(
                                text = data.mySuccessList[it].age ?: "Age not Avaliable"
                            )
                        },
                        leadingContent = {
                            AsyncImage(
                                model = data.mySuccessList[it].image,
                                contentDescription = null,
                                modifier = Modifier.size(100.dp)
                            )
                        }
                    )
                }
            }
        }

        is UIState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = data.error, fontSize = 28.sp)
            }
        }

        UIState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(150.dp)
                )
            }
        }
    }
}