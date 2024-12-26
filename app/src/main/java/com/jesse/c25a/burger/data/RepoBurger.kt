package com.jesse.c25a.burger.data

import com.jesse.c25a.burger.domain.model.SmallItem
import com.jesse.c25a.burger.domain.model.toDomain
import javax.inject.Inject

class RepoBurger @Inject constructor(private val servBurger: ServBurger){

    suspend fun getCharacters(): List<SmallItem> {
        return servBurger.getCharacters().map { it.toDomain()}
    }

}