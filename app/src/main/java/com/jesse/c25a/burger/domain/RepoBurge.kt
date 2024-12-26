package com.jesse.c25a.burger.domain

import com.jesse.c25a.burger.domain.model.SmallItem

interface RepoBurge {

    suspend fun getMyData(): List<SmallItem>

}
