package com.jesse.c25a.burger.domain

import com.jesse.c25a.burger.data.RepoBurger
import com.jesse.c25a.burger.domain.model.SmallItem
import javax.inject.Inject

class GetDataUC @Inject constructor(private val repoBurger: RepoBurger) {

    suspend operator fun invoke(): List<SmallItem> {
        return repoBurger.getCharacters()
    }
}