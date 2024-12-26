package com.jesse.c25a.burger.data

import com.jesse.c25a.burger.data.model.SmaillResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface SmallApi {

    @GET("characters")
    suspend fun getData(): Response<List<SmaillResponseItem>>

}