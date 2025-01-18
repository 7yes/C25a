package com.jesse.c25a.paging3tutorial.data.response

import com.google.gson.annotations.SerializedName
import com.jesse.c25a.paging3tutorial.data.response.CharacterResponse
import com.jesse.c25a.paging3tutorial.data.response.InfoResponse

data class ResponseWrapper(
    @SerializedName("info") val information: InfoResponse,
    @SerializedName("results") val results:List<CharacterResponse>,
)

