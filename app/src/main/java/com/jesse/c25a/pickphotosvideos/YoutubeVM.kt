package com.jesse.c25a.pickphotosvideos

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class YoutubeViewModel @Inject constructor() : ViewModel(){
    var imageUrl = mutableStateOf("")

    var videoUri: Uri? = null
    var isNewVideoPicked = mutableStateOf(false)
    private val _wasVideo = MutableLiveData<Boolean>()
    val wasVideo: LiveData<Boolean> = _wasVideo

    fun setVideo(isVideo: Boolean) {
        _wasVideo.value = isVideo
    }
}
