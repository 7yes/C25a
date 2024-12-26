package com.jesse.c25a.burger.domain

import com.jesse.c25a.burger.domain.model.SmallItem
import javax.inject.Inject

class GetDataUC @Inject constructor(private val repoBurge: RepoBurge) {

    suspend operator fun invoke(): List<SmallItem> {
        return repoBurge.getMyData()
    }
}