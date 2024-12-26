package com.jesse.c25a.burger.data.model

import com.jesse.c25a.burger.data.ServBurger
import com.jesse.c25a.burger.domain.RepoBurge
import com.jesse.c25a.burger.domain.model.SmallItem
import com.jesse.c25a.burger.domain.model.toDomain
import javax.inject.Inject

class RepoBurgerImpl @Inject constructor (private val servBurger: ServBurger): RepoBurge {
    override suspend fun getMyData(): List<SmallItem> {
        return servBurger.getCharacters().map { it.toDomain()}
    }
}
