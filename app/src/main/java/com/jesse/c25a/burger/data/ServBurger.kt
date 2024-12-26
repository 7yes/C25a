package com.jesse.c25a.burger.data

import com.jesse.c25a.burger.data.model.SmaillResponseItem
import javax.inject.Inject

class ServBurger @Inject constructor(private val smallApi: SmallApi) {

    suspend fun getCharacters(): List<SmaillResponseItem> {
        val call = smallApi.getData()
        return if (call.isSuccessful) {
            call.body() ?: emptyList()
        } else {
            emptyList()
        }
    }

}