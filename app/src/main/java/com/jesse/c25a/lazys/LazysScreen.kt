package com.jesse.c25a.lazys

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LazysScreen(){
    val items = listOf("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z")
    Column (Modifier
        .fillMaxSize()
        .background(Color.LightGray), horizontalAlignment = Alignment.CenterHorizontally){
        LazyColumn(Modifier.weight(1f).background(Color.White)) {
            items(items.size){
                Box(Modifier.size(50.dp)){Text(text = items[it])}
            }
        }
        LazyRow(Modifier
            .fillMaxWidth()
            .weight(1f).background(Color.Cyan)) {
            itemsIndexed(items){ index, item ->
                Box(Modifier.size(50.dp)){Text(text = item)}
            }
        }
        LazyVerticalGrid(modifier = Modifier.weight(1f).background(Color.Yellow),columns = GridCells.Fixed(3)) { // 3 columns
            items(items.size) { it ->
                Box(Modifier.size(50.dp)){Text(text = items[it])}
            }
        }
        LazyVerticalStaggeredGrid(modifier = Modifier.weight(1f).background(Color.Blue),columns = StaggeredGridCells.Fixed(3)) { // 3 columns
            items(items.size) { it ->
                Text(text = items[it], modifier = Modifier.background(Color.LightGray).height(if (items[it] == "b") 100.dp else 50.dp))
            }
        }
    }
}