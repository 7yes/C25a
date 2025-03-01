package com.jesse.c25a.youtube

import android.net.Uri
import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YouTubeScreen() {
    Column (Modifier.fillMaxWidth()){
        YoutubePlayer(youtubeVideoId = "u8ccGjar4Es", lifecycleOwner = LocalLifecycleOwner.current)
        VideoPlayer(videoUri = Uri.parse("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"))
    }
}

@Composable
fun YoutubePlayer(youtubeVideoId: String, lifecycleOwner: LifecycleOwner) {
    AndroidView(
        modifier = Modifier.fillMaxWidth().padding(8.dp).clip(RoundedCornerShape(8.dp)),
        factory = { context ->
        YouTubePlayerView(context = context).apply {
            lifecycleOwner.lifecycle.addObserver(this)
            addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.loadVideo(youtubeVideoId, 0f)
                   // youTubePlayer.pause() play() mute() unMute() seekTo(10f)
                }
            })
        }
    },
        update = {
            // This is called when the composable recomposes.
            // You can update the player here if needed.
        })
}

@Composable
fun VideoPlayer(
    videoUri: Uri
){
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp)),
        factory = {context->
        VideoView(context).apply {
            setVideoURI(videoUri)

            val mediaController = MediaController(context)
            mediaController.setAnchorView(this)

            setMediaController(mediaController)

            setOnPreparedListener {
                it.isLooping = true
                start()
            }
        }
    })
}