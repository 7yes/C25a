package com.jesse.c25a.filter.presentation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jesse.c25a.burger.presentation.uiState.UIStateFilter

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(filterVM: FilterVM = hiltViewModel()) {
    val state by filterVM.uiState.collectAsState()
    var text by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Cyan),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            SearchBar(
                query = text,
                onQueryChange = { text = it },
                onSearch = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                    updateDataList(text)
                },
                onActiveChange = { },
                active = false,
                modifier = Modifier
                    .background(Color.Cyan),
                placeholder = { Text(text = "Search") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
            ) {
            }
        }
        Column (Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            when (state) {
                is UIStateFilter.Success -> {
                    val data = (state as UIStateFilter.Success).mySuccessList.toMutableStateList()
                    Button(onClick = { data.add(1, data[0].copy(name = "New")) }) {
                        Text(text = "Add")}
                    LazyColumn(
                           Modifier
                               .padding(8.dp)
                       ) {
                           items(data.size) {
                               ListItem(
                                   headlineContent = { Text(text = data[it].name ?: "") },
                                   supportingContent = { Text(text = data[it].age ?: "") },
                                   leadingContent = {
                                       AsyncImage(
                                           model = data[it].image,
                                           contentDescription = null,
                                           modifier = Modifier.size(100.dp))
                                   },
                                   trailingContent = { Button(onClick = {
                                       data.removeAt(it)
                                   }) { Text("Delete") } }
                               )
                           }
                       }
                }

                is UIStateFilter.Error -> {
                    Text(text = (state as UIStateFilter.Error).error)
                }

                else -> CircularProgressIndicator(modifier = Modifier.size(150.dp))
            }
        }
    }

}

fun updateDataList(text: String) {
    Log.d("TAJ", "updateDataList: $text")
}
