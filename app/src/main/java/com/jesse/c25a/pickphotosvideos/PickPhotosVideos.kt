package com.jesse.c25a.pickphotosvideos

import android.util.Log
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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun PickPhotosVideos(viewModel: YoutubeViewModel = hiltViewModel()) {

    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                if (viewModel.wasVideo.value == true) {
                    viewModel.videoUri = uri
                    viewModel.isNewVideoPicked.value = true
                } else {
                    viewModel.imageUrl.value = uri.toString()
                }
            } else {
                Log.d("tag", "No media selected")
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
        if (viewModel.wasVideo.value == true) {
            Log.d("TAG", "PickPhotosVideos: fue viedeo ${viewModel.wasVideo.value} ")
            VideoScreen(viewModel.videoUri!!)
        } else {
            Log.d("TAG", "PickPhotosVideos: fue viedeo ${viewModel.wasVideo.value} ")
            DisplayImage(viewModel.imageUrl.value)
        }
        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(16.dp))
        Button(modifier = Modifier.padding(16.dp), onClick = {
            viewModel.setVideo(false)
            pickMedia.launch(
                PickVisualMediaRequest.Builder()
                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    .build()
            )
        }) { Text(text = "Pick Photos") }
        Button(modifier = Modifier.padding(16.dp), onClick = {
            viewModel.setVideo(true)
            pickMedia.launch(
                PickVisualMediaRequest.Builder()
                    .setMediaType(ActivityResultContracts.PickVisualMedia.VideoOnly)
                    .build()
            )
        }) { Text(text = "Pick Videos") }
        Log.d("TAG", "PickPhotosVideos:  $pickMedia.")
    }
}

@Composable
fun DisplayImage(image: String) {
    val context = LocalContext.current
    val imageSate: AsyncImagePainter.State = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(image)
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