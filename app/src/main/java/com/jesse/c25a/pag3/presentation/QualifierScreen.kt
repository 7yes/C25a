package com.jesse.c25a.pag3.presentation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jesse.c25a.pag3.presentation.uiState.UIState

@Composable
fun QualifierScreen(viewModel: ChatacterVM = hiltViewModel()) {
    val characters by viewModel.uiState.collectAsState()
    Log.d("TAJ", "Pag3Screen: $characters")

    when (characters) {
        is UIState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.size(64.dp))
            }
        }

        is UIState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column {
                    Text(text = "Error")
                    Text(text = (characters as UIState.Error).error)
                }
            }
        }

        is UIState.Success -> {
            LazyColumn(Modifier.fillMaxSize()) {
                val data = (characters as UIState.Success).mySuccessList
                if (data.isNotEmpty()) {
                    items(data.size) {
                        ListItem(modifier = Modifier.padding(8.dp),
                            tonalElevation = 10.dp,
                            shadowElevation = 10.dp,
                            headlineContent = { Text(text = data[it].name ?: "") },
                            supportingContent = { Text(text = data[it].status ?: "") },
                            overlineContent = {
                                Row (Modifier.fillMaxWidth()){
                                    Text(
                                        text = ("id: ${data[it].id} Specie: ${data[it].species}")
                                    )
                                }
                            },
                            leadingContent = {
                                   AsyncImage(
                                       model = data[it].image,
                                       contentDescription = null,
                                       modifier = Modifier.size(100.dp)
                                   )
                            }
                        )
                    }
                }
            }
        }
    }

}