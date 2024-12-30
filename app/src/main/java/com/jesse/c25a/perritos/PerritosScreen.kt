package com.jesse.c25a.perritos

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

@Composable
fun PerritosScreen() {

    val datos = rememberSaveable { mutableListOf<String>() }

    val deffered = CoroutineScope(Dispatchers.IO).launch {
        datos.addAll(getDogs("akita"))
    }
    runBlocking {
        deffered.join()
    }
    Log.d("TAJ", "PerritosScreen: ${datos}")

    if (datos.isNotEmpty()) {
        showDogs(datos)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showDogs(dogs: List<String>) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchText by rememberSaveable { mutableStateOf("") }
    Log.d("TAJ", "showDogs was called ")
    Column {
        SearchBar(
            query = searchText,
            onQueryChange = { searchText = it },
            active = false,
            onActiveChange = {},
            onSearch = {
                Log.d("TAJ", "onSearch was called $searchText")
                focusManager.clearFocus()
                keyboardController?.hide()
            },
            modifier = Modifier.padding(16.dp),
            content = {Text("Sear")},
            placeholder = { Text("Search") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(dogs.size) {
                AsyncImage(
                    model = dogs[it],
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

suspend fun getDogs(breed:String): List<String> {
    Log.d("TAJ", "getDogs was called ")
    val retrofit = getRetrofit()
    val perritosApi = retrofit.create(perritosApi::class.java)
    val data = mutableListOf<String>()
    val call = perritosApi.getDogs("$breed/images")
    if (call.isSuccessful) {
        val body = call.body()?.message
        val puppies = body ?: emptyList()
        data.addAll(puppies)
    }
    return data
}

interface perritosApi {
    @GET
    suspend fun getDogs(@Url url:String): Response<DogsResponse>
}

fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/breed/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}