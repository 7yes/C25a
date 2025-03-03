package com.jesse.c25a.pickphotosvideos

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun PickPhotosVideos() {

    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                println(uri.toString())
            }
        }
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        DisplayImage()
        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(16.dp))
        Button(modifier = Modifier.padding(16.dp), onClick = {
           // viewModel.isVideo = false
            pickMedia.launch(
            PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly)
                .build()
        ) }) { Text(text = "Pick Photos") }
        Button(modifier = Modifier.padding(16.dp), onClick = {
            //viewModel.isVideo = true
            pickMedia.launch(
            PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.VideoOnly)
                .build()
        ) }) { Text(text = "Pick Videos") }
    }
}

@Composable
fun DisplayImage() {
    val context = LocalContext.current
    val imageSate: AsyncImagePainter.State = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data("xxx")
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build()
    ).state

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                Color.LightGray
            )
    ) {
        if (imageSate is AsyncImagePainter.State.Success) {
            Image(
                modifier = Modifier.fillMaxSize(),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                bitmap = imageSate.result.drawable.toBitmap().asImageBitmap()
            )
        }
    }
}