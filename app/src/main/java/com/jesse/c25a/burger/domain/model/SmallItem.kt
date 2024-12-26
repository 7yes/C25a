package com.jesse.c25a.burger.domain.model

import com.jesse.c25a.burger.data.model.SmaillResponseItem

data class SmallItem (
    val age: String?,
    val image: String?,
    val name: String?,
    val url: String?
)

fun SmaillResponseItem.toDomain() = SmallItem(
    age = age,
    image = image,
    name = name,
    url = url
)
